package com.online.book.store.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.book.store.configuration.UserRegistration;
import com.online.book.store.repository.UserRepo;

@Controller
public class HomeController {

	@Autowired
	UserRepo repo;
	

	
	String print=null;

	static public String user_session;

	
	@RequestMapping("/")
	public ModelAndView Home()
	{
		ModelAndView mv = new ModelAndView("Home");
		return mv;
	}
	
	@RequestMapping("/Login")
	public ModelAndView Login(String print, String User, String Pass)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("PrintSwal",print);
		
		if(User!=null && Pass!=null)
		{
		mv.addObject("User_Name",User);
		mv.addObject("Pass",Pass);
		}
		else
		{
			mv.addObject("User_Name","admin");
			mv.addObject("Pass","admin");
		}
		
		mv.setViewName("Login_Form");
		
		return mv;
	}
	
	@RequestMapping("/User")
	public ModelAndView User()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Registration_Form");
		return mv;
	}
	
	@RequestMapping("/VerifyLogin")
	public ModelAndView VerifyLogin(String email, String password,UserController u1) 
	{
		ModelAndView mv = new ModelAndView("Login_Form");
		
		UserRegistration user = repo.findByEmailAndPassword(email, password);
		
		
		if(email.equals("admin") && password.equals("admin"))
		{
			print="Admin";	
			mv.addObject("PrintSwal",print);
			user_session="Admin";
			mv.setViewName("Admin_View");
		}
		else if(user !=null)
		{
			print="UserLogin";	
			//mv.addObject("PrintSwal",print);
			mv.addObject("User",user.getFullname());
			user_session=user.getFullname();
			//mv.setViewName("User_View");
			return u1.User_Home(user_session,print);
		}
		else
		{
			print="Failed";	
			mv.addObject("PrintSwal",print);
		}
		
		return mv;
	}

	
	@RequestMapping("/User_Registration")
	public ModelAndView User_Registration(UserRegistration ureg,String email, String password)
	{
		ModelAndView mv = new ModelAndView("Registration_Form");
			
		//Find Data Using Email
		Optional<UserRegistration> ureg1 = repo.findById(email);
		
			
		if(ureg1.isPresent())
		{
			
			print="User_Exists";		

		}
		else
		{
			
			//Save Data in Student Registration Table
			
			repo.save(ureg);
			
			print="Reg_Sucess";

			mv.addObject("PrintSwal",print);
			
			//mv.setViewName("Login_Form");
			return Login(print,email,password);

		}
		
		mv.addObject("PrintSwal",print);
		

		return mv;
	}
	
	
	
	
}
