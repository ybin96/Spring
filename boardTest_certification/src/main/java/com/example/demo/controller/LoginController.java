package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private MemberDAO dao;

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void form() {
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(String id, String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pwd", pwd);
		boolean re = dao.isMember(map);
		if(re == false) {
			mav.addObject("msg","로그인 실패");
			mav.setViewName("error");
		}else {
			session.setAttribute("loginUser", dao.findById(id));
		}
		return mav;
	}
}
