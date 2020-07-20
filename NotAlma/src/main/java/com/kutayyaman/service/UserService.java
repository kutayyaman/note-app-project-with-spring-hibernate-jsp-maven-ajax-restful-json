package com.kutayyaman.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kutayyaman.dao.UserDAO;
import com.kutayyaman.entity.Note;
import com.kutayyaman.entity.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserDAO userDAO;
	
	public Long insert(User user) {
		
		 String uuid = UUID.randomUUID().toString();
		 user.setKeyreg(uuid);
		
		 if(userDAO.insert(user)>0) {
			 mailService.registerMail(user.getEmail(), user.getKeyreg());
		 }
		 return 1l;
	}
	public void update(User user) {
		 userDAO.update(user);
	}
	
	
	public User getUserFindByUsernameAndPasswordAndActive(User user){
		
		return userDAO.getUserFindByUsernameAndPasswordAndActive(user.getUsername(), user.getPassword());
	}
	
	public User getUserFindByUsername(String username){
		return userDAO.getUserFindByUsername(username);
	}
	
	public boolean getUserFindByKey(String key){
		User user=userDAO.getUserFindByKey(key);
		
		
		if(user!=null) {
			user.setActive(true);
			update(user);
			return true;
		}
		else 
			return false;
	}
	
}
