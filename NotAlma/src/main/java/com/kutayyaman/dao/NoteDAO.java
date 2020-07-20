package com.kutayyaman.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kutayyaman.entity.Note;

@Repository
public class NoteDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Long insert(Note note) {
		return (Long) sessionFactory.getCurrentSession().save(note);
		
	}
	public void update(Note note) {
		sessionFactory.getCurrentSession().update(note);
	}
	public void persist(Note note) {
		sessionFactory.getCurrentSession().persist(note);
	}
	public void delete(Note note) {
		sessionFactory.getCurrentSession().delete(note);
	}
	public Note getNoteFindById(Long id){
		Query query=sessionFactory.getCurrentSession().createQuery("From Note WHERE id=:alan").setLong("alan", id);//Burdaki Note tablo adi degil direkt sinifin adi
		return (Note) query.getSingleResult();
	}
	public ArrayList<Note> getAll(){
		Query query=sessionFactory.getCurrentSession().createQuery("From Note");//Burdaki Note tablo adi degil direkt sinifin adi
		return (ArrayList<Note>) query.getResultList();
	}
	public ArrayList<Note> getAll(Long user_id){
		Query query=sessionFactory.getCurrentSession().createQuery("From Note WHERE user_id=:alan order by id desc").setLong("alan", user_id);//Burdaki Note tablo adi degil direkt sinifin adi
		
		return (ArrayList<Note>) query.getResultList();
	}
	
}
