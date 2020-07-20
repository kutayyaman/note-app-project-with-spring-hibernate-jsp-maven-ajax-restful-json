package com.kutayyaman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kutayyaman.notalma.HomeController;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void registerMail(String mail,String key) {
		
		SimpleMailMessage email=new SimpleMailMessage();
		email.setFrom("kutaynoteapp@gmail.com");
		email.setTo(mail);
		email.setSubject("kutayNoteApp Uyeligi Tamamla");
		email.setText("Uyelik Islemlerinizi Tamamlamak Icin Asagidaki Linke Tiklayin.\n\n"
		+HomeController.url+"/reg/"+key);
		
		mailSender.send(email);
		
		
	}
	
}
