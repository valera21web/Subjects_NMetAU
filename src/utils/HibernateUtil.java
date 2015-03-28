package utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {  
    private static SessionFactory sessionFactory;  
    
    static {
        try {
            sessionFactory = (new Configuration().configure()).buildSessionFactory();
        } catch (HibernateException he){ 
           System.out.println("It happened an error at the initialization of the SessionFactory: " + he); 
           throw new ExceptionInInitializerError(he); 
        } 
    }  

    public static SessionFactory getSessionFactory() throws HibernateException {
    	return sessionFactory;
    }
}
