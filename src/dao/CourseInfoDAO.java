package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import module.CourseInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;

public class CourseInfoDAO {

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
		public Integer save(CourseInfo pCourseInfo) throws HibernateException {
			Integer id = 0;

			try {
				initOperation();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				pCourseInfo.setChange(dateFormat.format(date));
				pCourseInfo.setCreated(dateFormat.format(date));
				id = (Integer) sesion.save(pCourseInfo);
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
		public void update(CourseInfo pCourseInfo) throws HibernateException {
			try {
				initOperation();
				sesion.update(pCourseInfo);
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
		public void delete(CourseInfo pCourseInfo) throws HibernateException {
			try {
				initOperation();
				sesion.delete(pCourseInfo);
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
		public CourseInfo get(Integer id) throws HibernateException {
			CourseInfo pCourseInfo = null;
			try {
				initOperation();
				pCourseInfo = (CourseInfo) sesion.get(CourseInfo.class, id);
			} finally {
				sesion.close();
			}

			return pCourseInfo;
		}
		
		@SuppressWarnings("unchecked")
		public List<CourseInfo> getByCourse(int pCourse) {
			Query query = null;
			List<CourseInfo> res = null;
		    try {
		    	 initOperation();
		         query = sesion.createQuery("from CourseInfo where course_id = :id_s");
		         query.setParameter("id_s", pCourse);
		         res = query.list();
		    } catch (Exception e) {
		    	
		    } finally {
		    	sesion.close();
		    }
		    return res;
		}

		/**
		 * 
		 * @return all contacts
		 * @throws HibernateException
		 */
		@SuppressWarnings("unchecked")
		public List<CourseInfo> getAll() throws HibernateException {
			List<CourseInfo> lista= null;

			try {
				initOperation();
				lista = sesion.createQuery("from CourseInfo").list();
			} finally {
				sesion.close();
			}

			return lista;
		}
		
		
		/**
		 * 
		 * @param query the query
		 * @return a list with all contacts that verifies the query
		 * @throws HibernateException
		 */
		@SuppressWarnings("unchecked")
		public List<CourseInfo> getAll(String query) throws HibernateException {
			List<CourseInfo> lista = null;

			try {
				initOperation();
				lista = sesion.createQuery("from CourseInfo " + query).list();
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
