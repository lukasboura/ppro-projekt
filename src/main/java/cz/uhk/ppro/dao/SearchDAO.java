package cz.uhk.ppro.dao;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HibernateUtil;

public class SearchDAO {
	
	public SearchDAO() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<User> search(String vyraz){
		
		
		ArrayList<String> vyrazy = new ArrayList<String>();
		ArrayList<User> vysledky = new ArrayList<User>();
		
		StringTokenizer st = new StringTokenizer(vyraz);
		
		while (st.hasMoreElements()) {
			vyrazy.add((String)st.nextElement());
		}
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	       
	        Query query;
        	query = session.createQuery("from User where username in (:vyrazy) or firstName in (:vyrazy) or lastName in (:vyrazy)");
        	query.setParameterList("vyrazy", vyrazy);
        	vysledky = (ArrayList<User>) query.list();
	        
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    return vysledky;
	}
}
