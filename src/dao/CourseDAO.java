package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import module.Course;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;

public class CourseDAO {


	//=======================================================================
	// Fields
	//=======================================================================
	private Session sesion;
	private Transaction tx; //to manage transaction on insert,update and delete.
	
	//=======================================================================
	// Methods
	//=======================================================================
	/**
	 * registers an specific contact on the db
	 * @param pContact the new contact
	 * @return its assigned ID (its assigned primary key)
	 * @throws HibernateException
	 */
	public Integer save(Course pCourse) throws HibernateException {
		Integer id = 0;

		try {
			initOperation();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pCourse.setChange(dateFormat.format(date));
			pCourse.setCreated(dateFormat.format(date));
			id = (Integer) sesion.save(pCourse);
			tx.commit();
		} catch (HibernateException he) {
			manageException(he);
			throw he;
		} finally {
			sesion.close();
		}

		return id;
	}

	/**
	 * updates an specific contact
	 * @param pContact contact to update it
	 * @throws HibernateException
	 */
	public void update(Course pCourse) throws HibernateException {
		try {
			initOperation();
			sesion.update(pCourse);
			tx.commit();
		} catch (HibernateException he) {
			manageException(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	/**
	 * deletes an specific contact
	 * @param pContact the contact that will be deleted
	 * @throws HibernateException
	 */
	public void delete(Course pCourse) throws HibernateException {
		try {
			initOperation();
			sesion.delete(pCourse);
			tx.commit();
		} catch (HibernateException he) {
			manageException(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	

	/**
	 * 
	 * @param id of the contact
	 * @return an specific contact with an id
	 * @throws HibernateException
	 */
	public Course get(Integer id) throws HibernateException {
		Course pCourse = null;
		try {
			initOperation();
			pCourse = (Course) sesion.get(Course.class, id);
		} finally {
			sesion.close();
		}

		return pCourse;
	}

	/**
	 * 
	 * @return all contacts
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<Course> getAll() throws HibernateException {
		List<Course> lista= null;

		try {
			initOperation();
			lista = sesion.createQuery("from Course").list();
		} finally {
			sesion.close();
		}

		return lista;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Course> getSubjectsByTeacher(int pTeacher) {
		Query query = null;
		List<Course> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("select distinct subject_id from Course subject_id where teacher_id = :id_teacher order by subject_id");
	         query.setParameter("id_teacher", pTeacher);
	         res = query.list();
	    } catch (Exception e) {
	    	System.out.println("ERROR = " + e);
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getYears() {
		Query query = null;
		List<Integer> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("select distinct year from Course order by year");
	         res = query.list();
	         
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getYearsByTeacher(int pTeacher) {
		Query query = null;
		List<Integer> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("select distinct year from Course where teacher_id = :id order by year");
	         query.setParameter("id", pTeacher);
	         res = query.list();
	         
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getYearsBySybject(int pSybject) {
		Query query = null;
		List<Integer> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("select distinct year from Course where subject_id = :id order by year");
	         query.setParameter("id", pSybject);
	         res = query.list();
	         
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getSybject(int pSybject) {
		Query query = null;
		List<Course> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("from Course where subject_id = :id_s");
	         query.setParameter("id_s", pSybject);
	         res = query.list();
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getByYear(int pYear) {
		Query query = null;
		List<Course> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("from Course where year = :id_s");
	         query.setParameter("id_s", pYear);
	         res = query.list();
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getSybjectWithoutTeacher(int pSybject) {
		Query query = null;
		List<Course> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("from Course where teacher_id = :id_t and subject_id = :id_s");
	         query.setParameter("id_s", pSybject);
	         query.setParameter("id_t", 0);
	         res = query.list();
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getByTeacher(int pTeacher) {
		Query query = null;
		List<Course> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("from Course where teacher_id = :id ");
	         query.setParameter("id", pTeacher);
	         res = query.list();
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getByTeacherYearSubject(int pTeacher, int pYear, int pSubject) {
		Query query = null;
		List<Course> res = null;
	    try {
	    	 initOperation();
	    	 String qw = "from Course where teacher_id = :id " + 
	    			 (pYear > 0 ? " and year = :year_n " : "") + 
	    			 (pSubject > 0 ? " and subject_id = :sub_id " : "");
	         query = sesion.createQuery(qw);
	         query.setParameter("id", pTeacher);
	         if(pYear > 0) 
	        	 query.setParameter("year_n", pYear);
	         if(pSubject > 0) 
	        	 query.setParameter("sub_id", pSubject);
	         
	         res = query.list();
	    } catch (Exception e) {} 
	    finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getByYearSubject(int pSubject,int pYear) {
		Query query = null;
		List<Course> res = null;
	    try {
	    	 initOperation();
	    	 String qw = "from Course where subject_id = :sub_id and year = :year_n";
	         query = sesion.createQuery(qw);
	         query.setParameter("year_n", pYear);
	         query.setParameter("sub_id", pSubject);
	         
	         res = query.list();
	    } catch (Exception e) {} 
	    finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	
	/**
	 * 
	 * @param query the query
	 * @return a list with all contacts that verifies the query
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<Course> getAll(String query) throws HibernateException {
		List<Course> lista = null;

		try {
			initOperation();
			lista = sesion.createQuery("from Course " + query).list();
		} finally {
			sesion.close();
		}

		return lista;
	}


	private void initOperation() throws HibernateException {
		sesion = HibernateUtil.getSessionFactory().openSession();
		tx = sesion.beginTransaction();
	}

	private void manageException(HibernateException he) throws HibernateException {
		tx.rollback(); //we have to do a rollback
		throw new HibernateException("An error happened when it was accessing to Contact", he);
	}

}
