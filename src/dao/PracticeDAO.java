package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import module.Practice;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;

public class PracticeDAO {


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
	public Integer save(Practice pPractice) throws HibernateException {
		Integer id = 0;

		try {
			initOperation();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pPractice.setChange(dateFormat.format(date));
			pPractice.setCreated(dateFormat.format(date));
			id = (Integer) sesion.save(pPractice);
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
	public void update(Practice pPractice) throws HibernateException {
		try {
			initOperation();
			sesion.update(pPractice);
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
	public void delete(Practice pPractice) throws HibernateException {
		try {
			initOperation();
			sesion.delete(pPractice);
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
	public Practice get(Integer id) throws HibernateException {
		Practice pPractice = null;
		try {
			initOperation();
			pPractice = (Practice) sesion.get(Practice.class, id);
		} finally {
			sesion.close();
		}

		return pPractice;
	}

	/**
	 * 
	 * @return all contacts
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<Practice> getAll() throws HibernateException {
		List<Practice> lista= null;

		try {
			initOperation();
			lista = sesion.createQuery("from Practice").list();
		} finally {
			sesion.close();
		}

		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getYears() {
		Query query = null;
		List<Integer> res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("select distinct year from Practice");
	         res = query.list();
	         
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	@SuppressWarnings("rawtypes")
	public Practice getByYear(int year) {
		Query query = null;
		Practice res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("from Practice where year = :id_s");
	         query.setParameter("id_s", year);
			 List a = query.list();
	         if(a.size() > 0) {
	        	 res = (Practice) a.get(0);
	         } else {
	        	 res = null;
	         }
	    } catch (Exception e) {
	    	
	    } finally {
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
	public List<Practice> getAll(String query) throws HibernateException {
		List<Practice> lista = null;

		try {
			initOperation();
			lista = sesion.createQuery("from Practice " + query).list();
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
