package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.CustomerService;
import com.example.demo.vo.CustomerVO;

import lombok.Setter;

@Controller
@Setter
public class CustomerController {
	
	@Autowired
	private CustomerService cs;
	
	@GetMapping("/customer/list")
	public void list(Model model) {
		model.addAttribute("list", cs.findAll());
	}
	
	@GetMapping("/customer/insert")
	public void insert() {		
	}
	
	@GetMapping("/customer/update/{custid}")
	public ModelAndView update(@PathVariable int custid) {	
		ModelAndView mav = new ModelAndView("/customer/update");
		mav.addObject("c", cs.findById(custid).get());
		return mav;	
	}
	
	
	@PostMapping("/customer/save")
	public ModelAndView save(CustomerVO c) {
		cs.save(c);
		ModelAndView mav = new ModelAndView("redirect:/customer/list");
		return mav;
	}
	
	@GetMapping("/customer/delete/{custid}")
	public ModelAndView delete(@PathVariable int custid) {
		cs.deleteById(custid);
		ModelAndView mav = new ModelAndView("redirect:/customer/list");
		return mav;
	}
	
	
	
	
}














