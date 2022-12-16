package com.example.demo.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import com.example.demo.dao.OrdersDAO;
import com.example.demo.dao.View_ListOrdersDAO;
import com.example.demo.dao.View_ListOrdersDAO2;
import com.example.demo.service.BookService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.View_ListOrdersService;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.View_ListOrders;

import lombok.Setter;

//@Controller
@Setter
public class OrdersController_old {
	@Autowired
	private OrdersService os;
	@Autowired
	private BookService bs;
	@Autowired
	private CustomerService cs;
	@Autowired
	private OrdersDAO dao;
	

	@Autowired
	private View_ListOrdersDAO view_ListOrdersDAO;
	
	@Autowired
	private View_ListOrdersDAO2 view_ListOrdersDAO2;
	
	
	@GetMapping("/orders/insert")
	public void insertForm(Model model) {
		model.addAttribute("orderid", os.getNextNo());
		model.addAttribute("c_list", cs.findAll());
		model.addAttribute("b_list", bs.findAll());
	}
	
	@PostMapping("/orders/insert")
	public ModelAndView insertSubmit(OrdersVO o) {
		os.insert(o);
		ModelAndView mav = new ModelAndView("redirect:/orders/list");		
		return mav;
	}
	
	
	@GetMapping("/orders/list")
	public void list(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@GetMapping("/orders/list2")
	public void list2(Model model, String keyword, String sort_colum, String search_column,   HttpSession session) {
		
		String clsName = "com.example.demo.service.View_ListOrdersService";
//		View_ListOrdersService
		
		
		/*
		String clsName = "com.example.demo.Person";
		String methodName = "pro";
		String name = "tiger";
		try {
			Class personClass = Class.forName(clsName);
			Object obj = personClass.newInstance();
			Method m = personClass.getMethod(methodName, String.class);
			m.invoke(obj, name);			
		}catch (Exception e) {
			System.out.println("예외발생"+e.getMessage());
		}*/
		
		
		List<View_ListOrders> list = null;		
		
		if( (keyword == null || keyword.equals("")) && session.getAttribute("keyword") != null ) {
			keyword = (String)session.getAttribute("keyword");
			search_column = (String)session.getAttribute("search_column");
		}
		
		if(sort_colum == null || sort_colum.equals("")) {
			sort_colum = "orderid";
		}
		
		//findByNameOrderByOrderid
		//findByBooknameOrderByOrderid
		//findAllByOrderByOrderid
		
		sort_colum = Character.toUpperCase(sort_colum.charAt(0)) + sort_colum.substring(1);
		
		String methodName = "";
		if(keyword == null || keyword.equals("")) {
			methodName = "findAllByOrderBy"+sort_colum;
		}else {
			search_column = Character.toUpperCase(search_column.charAt(0)) + search_column.substring(1);
			methodName = "findBy"+search_column+"OrderBy"+sort_colum;			
		}
		
		System.out.println("methodName:  " + methodName);
		
		try {
			Class execClass = Class.forName(clsName);
			Object obj = execClass.newInstance();
			
			if(keyword == null || keyword.equals("")) {
				Method m = execClass.getMethod(methodName,View_ListOrdersDAO.class );
				list =(List<View_ListOrders>) m.invoke(obj, view_ListOrdersDAO);
			}else {
				Method m = execClass.getMethod(methodName, String.class, View_ListOrdersDAO.class);
				list = (List<View_ListOrders>)m.invoke(obj, keyword, view_ListOrdersDAO);
			}
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		
		/*
		if(keyword == null || keyword.equals("")) {
			list = view_ListOrdersDAO.findAll(Sort.by(sort_colum));
			methodName = "findAllByOrderBy"+sort_colum;
		}else {
			System.out.println("search_column:"+search_column);
			System.out.println("keyword:"+keyword);
			list = view_ListOrdersDAO.find(search_column, keyword, sort_colum);
			search_column = Character.toUpperCase(search_column.charAt(0)) + search_column.substring(1);
			methodName = "findBy"+search_column+"OrderBy"+sort_colum;			
		}*/
		
		
		/*	
			if(sort_colum == null || sort_colum.equals("")) {
				sort_colum = "orderid";
			}
			
			list = view_ListOrdersDAO.findAll(Sort.by(sort_colum));
	*/	
		
	//	if(sort_colum == null || sort_colum.equals("")) {
	//		sort_colum = "orderid";
	//		list = view_ListOrdersDAO.find(sort_colum);
	//	}
				
		/*
		System.out.println("search_column:"+search_column);
		
		System.out.println("sort_colum:"+sort_colum);
		
		
		if( (keyword == null || keyword.equals("")) && session.getAttribute("keyword") != null ) {
			keyword = (String)session.getAttribute("keyword");
			search_column = (String)session.getAttribute("search_column");
		}
			
		if(sort_colum == null || sort_colum.equals("")) {
			sort_colum = "orderid";
		}
		
		if(keyword != null && !keyword.equals("") && sort_colum!=null && !sort_colum.equals("")) {
			//list = view_ListOrdersDAO.findByName(keyword);			
			//list = view_ListOrdersDAO.searchName(keyword);	
			
			switch(search_column) {
				case "name":
					switch(sort_colum) {
						case "orderid":list = view_ListOrdersDAO.findByNameOrderByOrderid(keyword);break;
						case "name":list = view_ListOrdersDAO.findByNameOrderByName(keyword);break;
						case "bookname":list = view_ListOrdersDAO.findByNameOrderByBookname(keyword);break;
						case "orderdate":list = view_ListOrdersDAO.findByNameOrderByOrderdate(keyword);break;
						case "saleprice":list = view_ListOrdersDAO.findByNameOrderBySaleprice(keyword);break;
						case "price":list = view_ListOrdersDAO.findByNameOrderByPrice(keyword);break;
					}					
					break;
				case "bookname":
					switch(sort_colum) {
						case "orderid":list = view_ListOrdersDAO.findByBooknameOrderByOrderid(keyword);break;
						case "name":list = view_ListOrdersDAO.findByBooknameOrderByName(keyword);break;
						case "bookname":list = view_ListOrdersDAO.findByBooknameOrderByBookname(keyword);break;
						case "orderdate":list = view_ListOrdersDAO.findByBooknameOrderByOrderdate(keyword);break;
						case "saleprice":list = view_ListOrdersDAO.findByBooknameOrderBySaleprice(keyword);break;
						case "price":list = view_ListOrdersDAO.findByBooknameOrderByPrice(keyword);break;
					}					
					break;
			}
			
		}else {
			//list  = view_ListOrdersDAO.findAll();
			switch(sort_colum) {
				case "orderid":list = view_ListOrdersDAO.findAllByOrderByOrderid();break;
				case "name":list = view_ListOrdersDAO.findAllByOrderByName();break;
				case "bookname":list = view_ListOrdersDAO.findAllByOrderByBookname();break;
				case "orderdate":list = view_ListOrdersDAO.findAllByOrderByOrderdate();break;
				case "saleprice":list = view_ListOrdersDAO.findAllByOrderBySaleprice();break;
				case "price":list = view_ListOrdersDAO.findAllByOrderByPrice();break;
			}
		}
		
		
		
		
		*/
		
		if(keyword != null && !keyword.equals("")) {
			session.setAttribute("keyword", keyword);
			session.setAttribute("search_column", search_column);
		}
		
		model.addAttribute("list",list);
	}
	
	@GetMapping("/orders/list3")
	public void list3(Model model) {
		model.addAttribute("list", view_ListOrdersDAO2.findAll());
	}
	
}













