package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CustomerDAO;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDAO dao;
	
	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/customerList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView();
		view.addObject("list",dao.findAll());
		return view;
	}
	
	@RequestMapping("/detailCustomer")
	public ModelAndView detail(int custid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", dao.findCustid(custid));
		return mav;
	}
}
