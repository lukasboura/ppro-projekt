package cz.uhk.ppro.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.ppro.domain.Foto;
import cz.uhk.ppro.service.HibernateUtil;

public class FotogalerieDAO {

	@Transactional
	public List<Foto> getAll(int id){
		List<Foto> fotogalerie = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        Query query;
        	query = session.createQuery("from Foto f where f.aktivita.id = :id").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        	query.setInteger("id", id);
        	fotogalerie = query.list();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    return fotogalerie;
		
	}
	@Transactional
	public void addFoto(Foto foto){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        
	        session.saveOrUpdate(foto);
	        
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
	public void deleteFotoById(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        
	        Query query;
        	query = session.createQuery("delete Foto f where f.id = :id");
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
	@SuppressWarnings("finally")
	@Transactional
	public Foto getFotoById(int id){
		Foto foto = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        
	        Query query;
        	query = session.createQuery("from Foto f where f.id = :id").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        	query.setInteger("id", id);
        	foto = (Foto)query.list().get(0);
	        
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	     return foto;   
	    }
		
	}
	
	
}
