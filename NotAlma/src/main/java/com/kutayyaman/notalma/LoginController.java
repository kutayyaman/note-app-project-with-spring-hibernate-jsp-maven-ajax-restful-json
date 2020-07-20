package com.kutayyaman.notalma;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kutayyaman.entity.Note;
import com.kutayyaman.entity.User;
import com.kutayyaman.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) //status adinda istege bagli bir parametre ekledi bu parametre /reg/{key} 'den gelcek yani eposta onaylarken login sayfasina yonlendirdiginde parametre yollicak.
	public String login(@RequestParam(value="status",required = false) String status ,Model model) {
		
		if(status!=null) {
			System.out.println(status);
			if(status.equals("ok"))
				model.addAttribute("status","Uyelik Islemleri Tamamlandi");
			else
				model.addAttribute("status", "Hata olustu tekrar deneyin!!!!");
		}
		
		return "login";
	}
	
	
	@RequestMapping(value = "/controlUser",method = RequestMethod.POST) //ajax ile gonderdigmiz veriyi @RequestBody seklinde aliyoruz onemli buda.
	public ResponseEntity<String> controlUser(@RequestBody User user,HttpServletRequest request){
		
		User userm=userService.getUserFindByUsernameAndPasswordAndActive(user);
		if(userm!=null) {
			request.getSession().setAttribute("user", userm);
			return new ResponseEntity<String>("OK",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("ERROR",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model,HttpServletRequest request) {
		
		
		
		return "register";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model,HttpServletRequest request) {
		
		request.getSession().setAttribute("user", null);
		
		return "redirect:/login";
	}
	
	
	@RequestMapping(value = "/reg/{key}", method = RequestMethod.GET) //{key} istege bagli parametreleri @PathVariable ile aliyor dikkat et onemli
	public String regOk(@PathVariable("key") String key,Model model) {
		
		if(userService.getUserFindByKey(key)) {
			return "redirect:/login?status=ok";
		}
		
		return "redirect:/login?status=error";
	}
	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user,HttpServletRequest request){
		
		int status=control(user);
		if(status!=0)
			return new ResponseEntity<String>(status+"",HttpStatus.OK);

		System.out.println(user.toString());
		
		if(userService.insert(user).equals(1l)) {
			return new ResponseEntity<String>("OK",HttpStatus.CREATED);
		}
		
		return new ResponseEntity<String>("ERROR",HttpStatus.CREATED);
	}
	
	private int control(User user) {
		if(!user.getPass2().equals(user.getPassword()))
			return 1;
		return 0;
	}
}
