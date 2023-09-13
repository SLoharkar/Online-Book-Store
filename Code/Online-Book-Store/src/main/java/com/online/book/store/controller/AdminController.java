package com.online.book.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.book.store.configuration.BookRegistration;
import com.online.book.store.configuration.OrderRegistration;
import com.online.book.store.configuration.UserRegistration;
import com.online.book.store.repository.BookRepo;
import com.online.book.store.repository.OrderRepo;
import com.online.book.store.repository.UserRepo;

@Controller
public class AdminController {

	@Autowired
	UserRepo repo;
	
	@Autowired
	BookRepo brepo;
	
	@Autowired
	OrderRepo orepo;
	
	

	static public String user_session;

	
	@RequestMapping("/Admin_Home")
	public ModelAndView Admin_Home()
	{
		ModelAndView mv = new ModelAndView("Admin_View");
		return mv;
	}
	
	@RequestMapping("/Book_Management")
	public ModelAndView Book_Management()
	{
		ModelAndView mv = new ModelAndView("Book_Management");
		return mv;
	}
	
	@RequestMapping("/selectoperation")
	public ModelAndView selectoperation(String book_operation)
	{
		ModelAndView mv = new ModelAndView("Book_Management");
		
		if(book_operation.equals("None"))
		{
			return mv;
		}
		else if(book_operation.equals("Add"))
		{
			mv.addObject("selectAdd","Add");
		}
		else if(book_operation.equals("Delete"))
		{
			mv.addObject("selectDelete","Delete");
		}
		return mv;
	}
	
	
	@RequestMapping("/book_Add")
	public ModelAndView book_Add(BookRegistration breg, String Book_title)
	{
		ModelAndView mv = new ModelAndView("Book_Management");
		
		Optional<BookRegistration> breg1 = brepo.findById(Book_title);
		
		if(breg1.isPresent())
		{
			mv.addObject("PrintSwal","Book_Exist");
		}
		else
		{
			mv.addObject("PrintSwal","Add_Sucess");
			brepo.save(breg);
		}
		
		return mv;
	}
	
	@RequestMapping("/book_Delete")
	public ModelAndView book_Delete(String Book_title)
	{
		ModelAndView mv = new ModelAndView("Book_Management");
		
		
		Optional<BookRegistration> breg1 = brepo.findById(Book_title);
		
		if(breg1.isPresent())
		{
			brepo.deleteById(Book_title);
			
			mv.addObject("PrintSwal","Delete_Sucess");
		}
		else
		{
			mv.addObject("PrintSwal","Delete_Failed");
		}
		
		return mv;
	}
	
	
	@RequestMapping("/Book_Details")
	public ModelAndView Book_Details()
	{
		ModelAndView mv = new ModelAndView("Book_Details");
		
		List<BookRegistration> breg1= brepo.findAll();

		if(breg1.isEmpty())
		{			
			mv.addObject("PrintSwal","Book_Details_Empty");
			
			mv.setViewName("Admin_View");
		}
		else
		{
			BookRegistration book=null;
			mv.addObject("BookArray",book);
			mv.addObject("BookObject",breg1);
			
		}
		
		return mv;
	}
	
	@RequestMapping("/User_Details")
	public ModelAndView User_Details()
	{
		ModelAndView mv = new ModelAndView("User_Details");
		
		List<UserRegistration> ureg1= repo.findAll();

		if(ureg1.isEmpty())
		{			
			mv.addObject("PrintSwal","User_Details_Empty");
			
			mv.setViewName("Admin_View");
		}
		else
		{
			UserRegistration user=null;
			mv.addObject("UserArray",user);
			mv.addObject("UserObject",ureg1);
			
			
		}
		
		return mv;
	}
	
	
	@RequestMapping("/Admin_Order_Details")
	public ModelAndView Admin_Order_Details()
	{
		ModelAndView mv = new ModelAndView("Admin_Order_Details");
		
		List<OrderRegistration> oreg1= orepo.findAll();

		if(oreg1.isEmpty())
		{
			mv.addObject("PrintSwal","Order_Details_Empty");
			
			mv.setViewName("Admin_View");
		}
		else
		{
			OrderRegistration order=null;
			mv.addObject("OrderArray",order);
			mv.addObject("OrderObject",oreg1);
			
		}
		
		
		return mv;
	}
	
	
}
