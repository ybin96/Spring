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

@Controller
@Setter
public class OrdersController {
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
				
		List<View_ListOrders> list = null;		
		
		if( (keyword == null || keyword.equals("")) && session.getAttribute("keyword") != null ) {
			keyword = (String)session.getAttribute("keyword");
			search_column = (String)session.getAttribute("search_column");
		}
		
		if(sort_colum == null || sort_colum.equals("")) {
			sort_colum = "orderid";
		}
		
		//검색X 문자졍렬		
		//검색X 숫자정렬		
		//검색O 문자정렬		
		//검색O 숫자정렬
		
		if(keyword == null || keyword.equals("")) {				
			if(sort_colum.equals("name") || sort_colum.equals("bookname") || sort_colum.equals("orderdate")) {
				//검색X 문자졍렬	
				list = view_ListOrdersDAO.findCharSort(sort_colum);
			}else {
				//검색X 숫자정렬	
				list = view_ListOrdersDAO.findNumSort(sort_colum);
			}
		}else {
			if(sort_colum.equals("name") || sort_colum.equals("bookname") || sort_colum.equals("orderdate")) {
				//검색O 문자정렬		
				list = view_ListOrdersDAO.findSearchCharSort(search_column, keyword, sort_colum);
			}else {
				//검색O 숫자정렬
				list = view_ListOrdersDAO.findSearchNumSort(search_column, keyword, sort_colum);
			}
		}		
		
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













