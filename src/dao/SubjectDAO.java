package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import module.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;

public class SubjectDAO {

	public SubjectDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	private Session session;
	private Transaction tx; //to manage transaction on insert,update and delete.
	
	/**
	 * registers an specific contact on the db
	 * @param pContact the new contact
	 * @return its assigned ID (its assigned primary key)
	 * @throws HibernateException
	 */
	public Integer save(Subject pSubject) throws HibernateException {
		Integer id = 0;

		try {
			initOperation();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pSubject.setChange(dateFormat.format(date));
			pSubject.setCreated(dateFormat.format(date));
			id = (Integer) session.save(pSubject);
			tx.commit();
		} catch (HibernateException he) {
			manageException(he);
			throw he;
		} finally {
			session.close();
		}

		return id;
	}

	/**
	 * updates an specific contact
	 * @param pContact contact to update it
	 * @throws HibernateException
	 */
	public void update(Subject pSubject) throws HibernateException {
		try {
			initOperation();
			session.update(pSubject);
			tx.commit();
		} catch (HibernateException he) {
			manageException(he);
			throw he;
		} finally {
			session.close();
		}
	}

	/**
	 * deletes an specific contact
	 * @param pContact the contact that will be deleted
	 * @throws HibernateException
	 */
	public void delete(Subject pSubject) throws HibernateException {
		try {
			initOperation();
			session.delete(pSubject);
			tx.commit();
		} catch (HibernateException he) {
			manageException(he);
			throw he;
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * @param id of the contact
	 * @return an specific contact with an id
	 * @throws HibernateException
	 */
	public Subject get(Integer id) throws HibernateException {
		Subject pSubject = null;
		try {
			initOperation();
			pSubject = (Subject) session.get(Subject.class, id);
		} finally {
			session.close();
		}

		return pSubject;
	}
	
	public Subject get(String name) throws HibernateException {
		Subject pSubject = null;
		try {
			initOperation();
			pSubject = (Subject) session.get(Subject.class, name);
		} finally {
			session.close();
		}

		return pSubject;
	}

	/**
	 * 
	 * @return all contacts
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<Subject> getAll() throws HibernateException {
		List<Subject> lista= null;

		try {
			initOperation();
			lista = session.createQuery("from Subject").list();
		} finally {
			session.close();
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
	public List<Subject> getAll(String query) throws HibernateException {
		List<Subject> lista = null;

		try {
			initOperation();
			lista = session.createQuery("from Subject " + query).list();
		} finally {
			session.close();
		}

		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<Subject> getAll(String query, Integer[] limit) throws HibernateException {
		List<Subject> lista = null;

		try {
			initOperation();
			lista = session.createQuery("from Subject " + query)
					.setFirstResult(limit[0])
					.setMaxResults(limit[1])
					.list();
		} finally {
			session.close();
		}

		return lista;
	}


	private void initOperation() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manageException(HibernateException he) throws HibernateException {
		tx.rollback(); //we have to do a rollback
		throw new HibernateException("An error happened when it was accessing to Contact", he);
	}

}
