package cz.uhk.ppro.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.ppro.domain.Komentar;
import cz.uhk.ppro.service.HibernateUtil;

public class KomentarDAO {
	
	public KomentarDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	public void addKomentar(Komentar komentar){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        session.save(komentar);
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
	public List<Komentar> getAll(int id){
		List<Komentar> komentare = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        Query query;
        	query = session.createQuery("from Komentar k where k.aktivita.id = :id order by k.datum desc").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        	query.setInteger("id", id);
        	komentare = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    return komentare;
		
	}
}
