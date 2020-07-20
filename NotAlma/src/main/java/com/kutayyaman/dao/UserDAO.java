package com.kutayyaman.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kutayyaman.entity.Note;
import com.kutayyaman.entity.User;

@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Long insert(User user) {
		return (Long) sessionFactory.getCurrentSession().save(user);
		
	}
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}
	
	
	public User getUserFindByUsernameAndPasswordAndActive(String username,String password){
		Query query=sessionFactory.getCurrentSession().createQuery("From User WHERE username=:username AND password=:password AND active=:active").setString("username", username).setString("password", password).setBoolean("active", true);//Burdaki Note tablo adi degil direkt sinifin adi
		User user=null;
		try {
			user= (User) query.getSingleResult();
		}
		catch (Exception e) {
			user=null;
		}
		return user;
	}
	
	public User getUserFindByUsername(String username){
		Query query=sessionFactory.getCurrentSession().createQuery("From User WHERE username=:username").setString("username", username);//Burdaki Note tablo adi degil direkt sinifin adi
		return (User) query.getSingleResult();
	}
	
	public User getUserFindByKey(String key){
		Query query=sessionFactory.getCurrentSession().createQuery("From User WHERE keyreg=:key").setString("key", key);//Burdaki Note tablo adi degil direkt sinifin adi
		User user=null;
		try {
			user= (User) query.getSingleResult();
		}
		catch (Exception e) {
			user=null;
		}
		return user;
	}
	
	
}
