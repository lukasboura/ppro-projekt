package cz.uhk.ppro.dao;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HibernateUtil;

/**
 * A custom DAO for accessing data from the database.
 *
 */
public class UserDAO {

	protected static Logger logger = Logger.getLogger("dao");
	
	@Transactional
	public User getUserByUserName(String userName) {
		
		User user = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from User u left join fetch u.aktivity where u.username = :username ");
	        	query.setString("username", userName);
	        	user = (User)query.list().get(0);
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	    }
	    return user;
	
	}
	@Transactional
	public User getUserById(int id) {
		
		User user = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from User u left join fetch u.aktivity where u.id = :id ");
	        	query.setInteger("id", id);
	        	user = (User)query.list().get(0);
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	    }
	    return user;
	    }
	@Transactional
	public void createNewUser(User user){
		
		String pass = user.getPassword();
		String conpass = user.getConfirmpassword();
		byte[] hash = pass.getBytes();
		byte[] hash2 = conpass.getBytes();
		String hashpass = DigestUtils.md5DigestAsHex(hash);
		String hashpass2 = DigestUtils.md5DigestAsHex(hash2);
		user.setPassword(hashpass);
		user.setConfirmpassword(hashpass2);
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	session.saveOrUpdate(user);
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
	public boolean checkUsername(String username){
		User user = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	Query query;
	        	query = session.createQuery("from User where username = :username ");
	        	query.setString("username", username);
	        	user = (User)query.uniqueResult();
	        session.getTransaction().commit();
	    } catch (HibernateException he) {
	        session.getTransaction().rollback();
	    } finally {
	        if (session != null) {
	            session.close();
	            }
	        
	    }
	    if (user !=null) {
			return true;
		} else {
			return false;
		}
	}
	@Transactional
	public void updateUser(User user){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        	session.update(user);
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
