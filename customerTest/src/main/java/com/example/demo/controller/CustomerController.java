package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

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
		mav.addObject("c", dao.findCustid(custid));
		return mav;
	}
	
	//@RequestMapping(value="/insertCustomer", method=RequestMethod.GET)
	@GetMapping("/insertCustomer")
	public void insertForm() {
	}
	
	//@ RequestMapping(value="/insertCustomer", method=RequestMethod.POST)
	@PostMapping("/insertCustomer")
	public ModelAndView insertSubmit(CustomerVO c) {
		ModelAndView mav = new ModelAndView();
		int re = dao.insert(c);
		if(re > 0) {
			mav.setViewName("redirect:customerList");
		}
		return mav;
	}
	
	@GetMapping("/updateCustomer")
	public ModelAndView updateForm(int custid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("c", dao.findCustid(custid));
		return mav;
	}
	
	@PostMapping("/updateCustomer")
	public ModelAndView updateSubmit(CustomerVO c) {
		ModelAndView mav = new ModelAndView();
		int re = dao.update(c);
		if(re > 0) {
			mav.setViewName("redirect:/customerList");
		}
		return mav;
	}
	
	@GetMapping("/deleteCustomer")
	public ModelAndView delete(int custid) {
		ModelAndView mav = new ModelAndView("redirect:/customerList");
		int re = dao.delete(custid);
		if(re <= 0) {
			mav.addObject("msg","고객삭제에 실패하였습니다");
			mav.setViewName("error");
		}
		return mav;
	}
}
