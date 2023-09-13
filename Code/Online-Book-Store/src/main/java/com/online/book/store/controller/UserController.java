package com.online.book.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.book.store.configuration.BookRegistration;
import com.online.book.store.configuration.OrderRegistration;
import com.online.book.store.repository.BookRepo;
import com.online.book.store.repository.OrderRepo;
import com.online.book.store.repository.UserRepo;

@Controller
public class UserController {

	@Autowired
	UserRepo repo;
	
	@Autowired
	BookRepo brepo;
	
	@Autowired
	OrderRepo orepo;
	
	static public String user_session1="User";
	
	

	@RequestMapping("/User_Home")
	public ModelAndView User_Home(String User_Session,String print)
	{
		ModelAndView mv = new ModelAndView("User_View");
		
		if(User_Session !=null) { user_session1=User_Session; }
		
		mv.addObject("User",user_session1);
		
		mv.addObject("PrintSwal",print);
		
		return mv;
	}
	
	@RequestMapping("/User_Books")
	public ModelAndView User_Books(String User_Session)
	{
		ModelAndView mv = new ModelAndView("Search_Book");
		
		mv.addObject("User",user_session1);
		
		return mv;
	}
	

	@RequestMapping("/user_select_operation")
	public ModelAndView user_select_operation(String book_operation)
	{
		ModelAndView mv = new ModelAndView("Search_Book");
		
		mv.addObject("User",user_session1);
		

		if(book_operation.equals("None"))
		{
			return mv;
		}
		else if(book_operation.equals("Search"))
		{
			mv.addObject("selectSearch","Search");
		}
		else if(book_operation.equals("Display"))
		{
			mv.addObject("selectDisplay","Display");
			return User_Book_Details(mv);
		}
		else if(book_operation.equals("R&R"))
		{
			mv.addObject("selectRR","RR");
		}
		
		return mv;
	}
	
	
	
	@RequestMapping("/user_search_Book")
	public ModelAndView user_search_Book(String Book_title)
	{
		ModelAndView mv = new ModelAndView("Search_Book");
		
		mv.addObject("User",user_session1);
		
		Optional<BookRegistration> breg1 = brepo.findById(Book_title);
		
		if(breg1.isPresent())
		{
			mv.addObject("PrintSwal","Book_Found");
		}
		else
		{
			mv.addObject("PrintSwal","Book_Not_Found");
		}
		return mv;
		
	}
	
	
	
	
	
	@RequestMapping("/User_Book_Details")
	public ModelAndView User_Book_Details(ModelAndView mv)
	{	
		
		List<BookRegistration> breg1= brepo.findAll();

		if(breg1.isEmpty())
		{
			mv.addObject("PrintSwal","Book_Details_Empty");
			
			mv.setViewName("Search_Book");
		}
		else
		{
			BookRegistration book=null;
			mv.addObject("BookArray",book);
			mv.addObject("BookObject",breg1);
			
		}
		
		return mv;
		
	}
	
	
	
	@RequestMapping("/user_Rate_Book")
	public ModelAndView user_Rate_Book(String Book_title,String Reviews,String rate)
	{
		ModelAndView mv = new ModelAndView("Search_Book");
		
		mv.addObject("User",user_session1);
		
		Optional<BookRegistration> breg1 = brepo.findById(Book_title);
		
		if(breg1.isPresent())
		{
			mv.addObject("PrintSwal","RBook_Found");
			BookRegistration breg = breg1.get();
			breg.setRate(rate);
			breg.setReviews(Reviews);
			brepo.save(breg);
		}
		else
		{
			mv.addObject("PrintSwal","RBook_Not_Found");
		}
		return mv;
		
	}
	
	
	@RequestMapping("/User_Buy_Book")
	public ModelAndView User_Buy_Book()
	{
		ModelAndView mv = new ModelAndView("User_Buy_Book");
		
		mv.addObject("User",user_session1);
		
		return mv;
	}
	
	
	
	@RequestMapping("/user_Search_Buy_Book")
	public ModelAndView user_Search_Buy_Book(String Book_title)
	{
		ModelAndView mv = new ModelAndView("User_Buy_Book");
		
		mv.addObject("User",user_session1);
		
		
		Optional<BookRegistration> breg1 = brepo.findById(Book_title);
		
		if(breg1.isPresent())
		{
			BookRegistration breg =breg1.get();
			mv.addObject("Bname",breg.getBook_title());
			mv.addObject("Bprice",breg.getPrice());
			mv.addObject("PrintSwal","Book_Found");
			
		}
		else
		{
			mv.addObject("PrintSwal","Book_Not_Found");
		}
		
		return mv;
	}
	
	@RequestMapping("/user_Save_Buy_Book")
	public ModelAndView user_Save_Buy_Book(OrderRegistration oreg)
	{
		ModelAndView mv = new ModelAndView("User_Buy_Book");
		
		mv.addObject("User",user_session1);

		oreg.setBusername(user_session1);
		
		orepo.save(oreg);
		
		mv.addObject("PrintSwal","Buy_Sucess");
		
		return mv;
	}
	
	
	@RequestMapping("/Order_Details")
	public ModelAndView Order_Details()
	{
		ModelAndView mv = new ModelAndView("Order_Details");
		
		mv.addObject("User",user_session1);
		
		List<OrderRegistration> oreg1= orepo.findAll();
		
		List<OrderRegistration> oreg2= orepo.findByBusername(user_session1);

		if(oreg1.isEmpty())
		{
			mv.addObject("PrintSwal","Order_Details_Empty");
			
			mv.setViewName("User_View");
		}
		else if(oreg2.isEmpty())
		{
			mv.addObject("PrintSwal","Order_Details_Empty");
			
			mv.setViewName("User_View");	
		}
		else
		{
			OrderRegistration order=null;
			mv.addObject("OrderArray",order);
			mv.addObject("OrderObject",oreg2);		
			
		}
		
		return mv;
		
	}
	
	
}
