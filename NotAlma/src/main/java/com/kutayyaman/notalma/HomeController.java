package com.kutayyaman.notalma;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kutayyaman.entity.Note;
import com.kutayyaman.security.LoginFilter;
import com.kutayyaman.service.MailService;
import com.kutayyaman.service.NoteService;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	public static String url="http://localhost:8080/notalma";
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private MailService mailService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homes(Locale locale, Model model) {
		
		
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		
		model.addAttribute("user",request.getSession().getAttribute("user"));
		System.out.println(request.getRemoteAddr());
		model.addAttribute("baslik", "Not Tutma" );
		
		//model.addAttribute("notlar", noteService.getAll(1l)); //bu kotu bir yontem bunun yerine ajax kullan ki zaten oyle yaptik.
		
		return "index";
	}
	
	@RequestMapping(value = "/detay/{id}", method = RequestMethod.GET)
	public String home(@PathVariable("id") Long id , Model model) {
		
		model.addAttribute("id",id);
		
		//mailService.registerMail("yamankutay1@gmail.com", "123");
		
		return "detail";
	}
	
	@RequestMapping(value = "/ekle", method = RequestMethod.GET)
	public String ekle(Model model) {
		
		
		
		return "addNote";
	}
	
	/*@RequestMapping(value = "/kutay", method = RequestMethod.GET)
	public String home(Model model)throws HibernateException,PropertyVetoException,SQLException {
		
		Kisi kisi=new Kisi();
		kisi.setAd("kutay");
		kisi.setSoyad("yaman");
		Connection connection=new Connection();
		connection.sessionFactory().getCurrentSession().save(kisi);
		
		model.addAttribute("serverTime", "Kutay" );
		
		return "home";
	}*/
	@RequestMapping(value = "/error_404", method = RequestMethod.GET)
	public String error(Locale locale, Model model) {
		
		return "error_404";
	}
	
	@RequestMapping(value = "/addNote",method = RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody Note note,HttpServletRequest request){
		
		
		System.out.println(note.toString());
		
		noteService.createNote(note,request);
		
		return new ResponseEntity<String>("Notunuz Eklendi.",HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateNote",method = RequestMethod.POST)
	public ResponseEntity<String> updateNote(@RequestBody Note note,HttpServletRequest request){
		Note oldNote=noteService.getFindById(note.getId());
		oldNote.setTitle(note.getTitle());
		oldNote.setContent(note.getContent());
		
		noteService.updateNote(oldNote, request);
		
		return new ResponseEntity<String>("Notunuz Eklendi.",HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteNote",method = RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@RequestBody Note note,HttpServletRequest request){
		Note oldNote=noteService.getFindById(note.getId());
		
		noteService.deleteNote(oldNote, request);
		
		return new ResponseEntity<String>("Notunuz Silindi.",HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/getNotes",method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getNotes(HttpServletRequest request){
		
		return new ResponseEntity<>(noteService.getAll(LoginFilter.user.getId()),HttpStatus.CREATED);
	}
	@RequestMapping(value = "/getNote",method = RequestMethod.POST)
	public ResponseEntity<Note> getNotes(@RequestBody String id,HttpServletRequest request){
		System.out.println(id);
		Note note=noteService.getFindById(Long.parseLong(id));
		if(note.getUser_id().equals(LoginFilter.user.getId()))
			return new ResponseEntity<>(noteService.getFindById(Long.parseLong(id)),HttpStatus.CREATED);
		
		return new ResponseEntity<>(null,HttpStatus.CREATED);
	}
}
