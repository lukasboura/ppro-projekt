package cz.uhk.ppro.dao;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cz.uhk.ppro.domain.PolozkaTreninkovehoPlanu;
import cz.uhk.ppro.service.HibernateUtil;


public class PlanDAO {

	public Collection<PolozkaTreninkovehoPlanu> getAllPolozkyByUserId(int id){
		
		Collection<PolozkaTreninkovehoPlanu> polozky = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from PolozkaTreninkovehoPlanu where user.id = :user_id");
	        	query.setInteger("user_id", id);
	        	polozky = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	    }
	    
	    return polozky;
		
	}
	
	public Collection<PolozkaTreninkovehoPlanu> getAllPolozkyByUserIdAndDay(int id, String den){
		
		Collection<PolozkaTreninkovehoPlanu> polozky = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from PolozkaTreninkovehoPlanu where user.id = :user_id and den = :den");
	        	query.setInteger("user_id", id);
	        	query.setString("den", den);
	        	polozky = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	    }
	    
	    return polozky;
		
	}
	
	public void addPlolozka(PolozkaTreninkovehoPlanu polozka){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	session.saveOrUpdate(polozka);
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	    }
		
	}
	public void deletePlan(int user_id){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("delete PolozkaTreninkovehoPlanu where user.id = :user_id");
	        	query.setInteger("user_id", user_id);
	        	query.executeUpdate();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	    }
		
	}
public void deletePolozku(int id){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("delete PolozkaTreninkovehoPlanu where id = :id");
	        	query.setInteger("id", id);
	        	query.executeUpdate();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	    }
		
	}
}
