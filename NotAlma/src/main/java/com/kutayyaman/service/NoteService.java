package com.kutayyaman.service;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kutayyaman.dao.NoteDAO;
import com.kutayyaman.dto.UserLoginDTO;
import com.kutayyaman.entity.Note;
import com.kutayyaman.entity.User;
import com.kutayyaman.security.LoginFilter;

@Service
@Transactional
public class NoteService {
	
	@Autowired
	private NoteDAO noteDAO;
	@Autowired
	private UserService userService;
	
	public Long createNote(Note note,HttpServletRequest request) {
		note.setUser_id(LoginFilter.user.getId());
		return noteDAO.insert(note);
	}
	public Long updateNote(Note note,HttpServletRequest request) {
		 noteDAO.update(note);
		 return 1l;
	}
	public Long deleteNote(Note note,HttpServletRequest request) {
		 noteDAO.delete(note);
		 return 1l;
	}
	public ArrayList<Note> getAll(){
		return noteDAO.getAll();
	}
	public ArrayList<Note> getAll(Long userId){
		return noteDAO.getAll(userId);
	}
	public Note getFindById(Long id){
		return noteDAO.getNoteFindById(id);
	}
	public ArrayList<Note> getAll(UserLoginDTO login){
		User userm=new User();
		userm.setUsername(login.getUsername());
		userm.setPassword(login.getPassword());
	
		User user=userService.getUserFindByUsernameAndPasswordAndActive(userm);
		
		return noteDAO.getAll(user.getId());
	}
	
}
