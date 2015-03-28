package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import module.Dyplom;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;

public class DyplomDAO {


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
	public Integer save(Dyplom pDyplom) throws HibernateException {
		Integer id = 0;

		try {
			initOperation();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pDyplom.setChange(dateFormat.format(date));
			pDyplom.setCreated(dateFormat.format(date));
			id = (Integer) sesion.save(pDyplom);
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
	public void update(Dyplom pDyplom) throws HibernateException {
		try {
			initOperation();
			sesion.update(pDyplom);
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
	public void delete(Dyplom pDyplom) throws HibernateException {
		try {
			initOperation();
			sesion.delete(pDyplom);
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
	public Dyplom get(Integer id) throws HibernateException {
		Dyplom pDyplom = null;
		try {
			initOperation();
			pDyplom = (Dyplom) sesion.get(Dyplom.class, id);
		} finally {
			sesion.close();
		}

		return pDyplom;
	}

	/**
	 * 
	 * @return all contacts
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<Dyplom> getAll() throws HibernateException {
		List<Dyplom> lista= null;

		try {
			initOperation();
			lista = sesion.createQuery("from Dyplom").list();
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
	         query = sesion.createQuery("select distinct year from Dyplom");
	         res = query.list();
	         
	    } catch (Exception e) {
	    	
	    } finally {
	    	sesion.close();
	    }
	    return res;
	}
	
	public Dyplom getByYear(int year) {
		Query query = null;
		Dyplom res = null;
	    try {
	    	 initOperation();
	         query = sesion.createQuery("from Dyplom where year = :id_s");
	         query.setParameter("id_s", year);
	         @SuppressWarnings("rawtypes")
			 List a = query.list();
	         if(a.size() > 0) {
	        	 res = (Dyplom) a.get(0);
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
	public List<Dyplom> getAll(String query) throws HibernateException {
		List<Dyplom> lista = null;

		try {
			initOperation();
			lista = sesion.createQuery("from Dyplom " + query).list();
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
