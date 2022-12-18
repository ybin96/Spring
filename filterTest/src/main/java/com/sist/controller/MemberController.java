package com.sist.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public void loginForm() {
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ModelAndView loginSubmit(String id,String pwd,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(id.equals("tiger") && pwd.equals("1234")) {
			session.setAttribute("id", id);
			System.out.println("로그인");
			mav.setViewName("redirect:/index.do");
		}else {
			mav.setViewName("redirect:/login.do");
		}
		return mav;
	}
}
