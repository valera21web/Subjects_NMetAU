package dao;

import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

import libs.Cript;
import libs.XMLSettings;
import module.Users;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;

/**
 * Our Data access object class to access to our contacts on the data base.
 * @author QuickGuid4Engineers <http://quickguide4engineers.blogspot.com/>
 *
 */
public class UsersDAO {
	//=======================================================================
	// Fields
	//=======================================================================
	private Session sesion;
	private Transaction tx; //to manage transaction on insert,update and delete.
	private JEditorPane show;
	private XMLSettings settingsRead;
	

	//=======================================================================
	// Methods
	//=======================================================================
	/**
	 * registers an specific contact on the db
	 * @param pContact the new contact
	 * @return its assigned ID (its assigned primary key)
	 * @throws HibernateException
	 */
	public UsersDAO() {
		show = new JEditorPane();
		root();
	}
	public UsersDAO(JEditorPane pShow) {
		show = pShow;
		root();
	}
	
	private void root() {
		
		try {
			settingsRead = new XMLSettings();
			String login = settingsRead.getVariable(XMLSettings.VARS_DEFAULT_USER, "user_login");
			String rassword = settingsRead.getVariable(XMLSettings.VARS_DEFAULT_USER, "user_password");
			int level = settingsRead.getVariableInt(XMLSettings.VARS_DEFAULT_USER, "user_level");
			
			if(!isLogin(login, rassword)) {
				Users rootUser = new Users();
				rootUser.setLevel(level);
				rootUser.setName(login);
				rootUser.setPassword(rassword);
				this.save(rootUser);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Integer save(Users pContact) throws HibernateException {
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
	public void update(Users pContact) throws HibernateException {
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
	public void delete(Users pContact) throws HibernateException {
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
	
	public boolean isLogin(String name, String password) {
		int count = 0;
	    try {
	    	 initOperation();
	    	 Query query = null;
	         query = sesion.createQuery("from Users where name = :id_n and password = :id_p");
	         query.setParameter("id_n", name);
	         query.setParameter("id_p", Cript.cache(password, Users.keyForCript));
	         count = query.list().size();
	    } catch (Exception e) {
	    	System.out.print(e);
	    } finally {
	    	sesion.close();
	    }
	    return count == 0 ? false : true;
	}
	
	public boolean hasUser(String name) {
		int count = 0;
	    try {
	    	 initOperation();
	    	 Query query = null;
	         query = sesion.createQuery("from Users where name = :id_n");
	         query.setParameter("id_n", name);
	         count = query.list().size();
	    } catch (Exception e) {
	    	System.out.print(e);
	    } finally {
	    	sesion.close();
	    }
	    return count == 0 ? false : true;
	}
	
	public Users getUserByName(String name) {
		Users user = new Users();
	    try {
	    	 initOperation();
	    	 Query query = null;
	         query = sesion.createQuery("from Users where name = :id_n");
	         query.setParameter("id_n", name);
	         if(query.list().size() > 0) {
	        	 user = ((Users) query.list().get(0));
	         }
	    } catch (Exception e) {
	    	System.out.print(e);
	    } finally {
	    	sesion.close();
	    }
	    return user;
	}

	/**
	 * 
	 * @param id of the contact
	 * @return an specific contact with an id
	 * @throws HibernateException
	 */
	public Users get(Integer id) throws HibernateException {
		Users pContact = null;
		try {
			initOperation();
			pContact = (Users) sesion.get(Users.class, id);
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
	public List<Users> getAll() throws HibernateException {
		List<Users> lista= null;

		try {
			initOperation();
			lista = sesion.createQuery("from Users").list();
		} finally {
			sesion.close();
		}

		return lista;
	}
	
	public int qwerty() {
	
	    try{
	         sesion.beginTransaction();
	           SQLQuery query = sesion.createSQLQuery("SELECT * FROM " + "table" + "WHERE stuff= ?");
	           query.setParameter(0, "params");
	         sesion.getTransaction().commit();
	    } catch (Exception e) {} 
	    sesion.close();
	    return 1;
	}
	
	
	/**
	 * 
	 * @param query the query
	 * @return a list with all contacts that verifies the query
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<Users> getAll(String query) throws HibernateException {
		List<Users> lista = null;

		try {
			initOperation();
			lista = sesion.createQuery("from Users " + query).list();
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
		show.setDocument((Document) new HibernateException("An error happened when it was accessing to Contact", he));
		JOptionPane.showConfirmDialog(null, he, "About", JOptionPane.PLAIN_MESSAGE);
		throw new HibernateException("An error happened when it was accessing to Contact", he);
	}
	
}
