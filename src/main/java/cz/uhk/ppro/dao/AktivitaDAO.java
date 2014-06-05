package cz.uhk.ppro.dao;


import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.ppro.domain.Aktivita;
import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HibernateUtil;

public class AktivitaDAO {
	
	@Transactional
	public void create(Aktivita entity){
				
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        session.saveOrUpdate(entity);
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	}
	@Transactional
	public Aktivita getById(int entity_id){
		
		Aktivita aktivita = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a left join fetch a.komentare where a.id = :aktivita_id").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setInteger("aktivita_id", entity_id);
	        	aktivita = (Aktivita)query.list().get(0);
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivita;
	    
	}
	@Transactional
	public void update(Aktivita entity){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	session.update(entity);
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	}
	@Transactional
	public void delete(Aktivita entity){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	session.delete(entity);
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	}
	@Transactional
	public int getPocetAktivit(String typ, int user_id){
		
		List<Aktivita> aktivity = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a where a.user.id = :user_id and a.typ = :typ").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setInteger("user_id", user_id);
	        	query.setString("typ", typ);
	        	aktivity = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivity.size();
	    
	}
	
	@Transactional
	public List<Aktivita> getAllAktivitById(int user_id){
		
		List<Aktivita> aktivity = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a left join fetch a.komentare where a.user.id = :user_id order by a.datum desc").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setInteger("user_id", user_id);
	        	aktivity = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivity;
	    
	}
	@Transactional
	public List<Aktivita> getAllAktivitByIdAndTime(int user_id, Date pocatek, Date konec){
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = formatter.format(pocatek);
		Timestamp from = Timestamp.valueOf(s);
		
		String ss = formatter.format(konec); 
		Timestamp to = Timestamp.valueOf(ss);
		
		List<Aktivita> aktivity = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a left join fetch a.komentare where a.user.id = :user_id and a.datum >= :pocatek and a.datum <= :konec order by a.datum desc").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setInteger("user_id", user_id);
	        	query.setTimestamp("pocatek", from);
	        	query.setTimestamp("konec", to);  
	        	aktivity = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivity;
	    
	}
	@Transactional
	public List<Aktivita> getAllAktivitByIdAndTyp(int user_id,String typ){
		
		List<Aktivita> aktivity = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a left join fetch a.komentare where a.user.id = :user_id and typ = :typ order by a.datum desc").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setInteger("user_id", user_id);
	        	query.setString("typ", typ);
	        	aktivity = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivity;
	    
	}
	@Transactional
	public List<Aktivita> getAllAktivitYear(int user_id,int year) throws ParseException{
		
		List<Aktivita> aktivity = null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date fromDate = df.parse("1-1-" + year + " 00:00:00");
		Date toDate = df.parse("31-12-" + year + " 23:59:59");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a left join fetch a.komentare where a.user.id = :id and a.datum between :from and :to").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setInteger("id", user_id);
	        	query.setDate("from", fromDate);
	        	query.setDate("to", toDate);
	        	aktivity = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivity;
	    
	}
	
	@Transactional
	public List<Aktivita> getAllAktivitYearMonthType(int user_id,int year,int month,String typ) throws ParseException{
		
		List<Aktivita> aktivity = null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date fromDate = df.parse("1-" + month + "-" + year + " 00:00:00");
		Date toDate = df.parse("31-"+ month + "-" + year + " 23:59:59");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a left join fetch a.komentare where a.user.id = :id and a.typ = :typ and a.datum between :from and :to").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setInteger("id", user_id);
	        	query.setDate("from", fromDate);
	        	query.setDate("to", toDate);
	        	query.setString("typ", typ);
	        	aktivity = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivity;
	    
	}
	
	@Transactional
	public List<Aktivita> getAllAktivitYearMonth(int user_id,int year,int month) throws ParseException{
		
		List<Aktivita> aktivity = null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date fromDate = df.parse("1-" + month + "-" + year + " 00:00:00");
		Date toDate = df.parse("31-"+ month + "-" + year + " 23:59:59");
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a left join fetch a.komentare where a.user.id = :id and a.datum between :from and :to").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setInteger("id", user_id); 
	        	query.setDate("from", fromDate);
	        	query.setDate("to", toDate);
	        	aktivity = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivity;
	    
	}
	@Transactional
	public List<Aktivita> getAllAktivitPratel(Collection<User> pratele){
		
		List<Aktivita> aktivity = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from Aktivita a left join fetch a.komentare where a.user in (:pratele) order by a.datum desc").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        	query.setParameterList("pratele", pratele);
	        	aktivity = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    
	    return aktivity;
	    
	}
	

	
	
	
}
