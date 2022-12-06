package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

@Controller
@RequestMapping("/insertMember")
public class InsertMemberController {
	
	@Autowired
	private MemberDAO dao;

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void form() {
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(MemberVO m) {
		ModelAndView mav = new ModelAndView("redirect:/login");
		int re = dao.insert(m);
		if(re > 0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
		return mav;
	}
}
