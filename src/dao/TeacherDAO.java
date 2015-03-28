package dao;

import java.util.List;
import module.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 * Our Data access object class to access to our contacts on the data base.
 * @author QuickGuid4Engineers <http://quickguide4engineers.blogspot.com/>
 *
 */
public class TeacherDAO {
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
	public Integer save(Teacher pContact) throws HibernateException {
		Integer id = 0;

		try {
			initOperation();
			id = (Integer) sesion.save(pContact);
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
	public void update(Teacher pContact) throws HibernateException {
		try {
			initOperation();
			sesion.update(pContact);
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
	public void delete(Teacher pContact) throws HibernateException {
		try {
			initOperation();
			sesion.delete(pContact);
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
	public Teacher get(Integer id) throws HibernateException {
		Teacher pContact = null;
		try {
			initOperation();
			pContact = (Teacher) sesion.get(Teacher.class, id);
		} finally {
			sesion.close();
		}

		return pContact;
	}

	/**
	 * 
	 * @return all contacts
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<Teacher> getAll() throws HibernateException {
		List<Teacher> lista= null;

		try {
			initOperation();
			lista = sesion.createQuery("from Teacher").list();
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
	public List<Teacher> getAll(String query) throws HibernateException {
		List<Teacher> lista = null;

		try {
			initOperation();
			lista = sesion.createQuery("from Teacher " + query).list();
		} finally {
			sesion.close();
		}

		return lista;
	}


	private void initOperation() throws HibernateException {
		System.out.println("init start");
		sesion = HibernateUtil.getSessionFactory().openSession();
		System.out.println("init get session");
		tx = sesion.beginTransaction();
	}

	private void manageException(HibernateException he) throws HibernateException {
		tx.rollback(); //we have to do a rollback
		throw new HibernateException("An error happened when it was accessing to Contact", he);
	}
	
}
