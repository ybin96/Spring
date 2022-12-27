package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class MemberController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/member/join")
	public void joinForm() {		
	}
	
	@PostMapping("/member/join")
	public ModelAndView joinSubmit(Member m) {
//		String encPwd = passwordEncoder.encode(m.getPwd());
//		m.setPwd(encPwd);
		ModelAndView mav = new ModelAndView("redirect:/member/login");
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		try {
			memberDAO.save(m);
		}catch (Exception e) {
			mav.addObject("msg", "회원가입에 실패하였습니다.");
			mav.setViewName("error");
		}
		
		/*
		memberDAO.save(m);
		Optional<Member> obj = memberDAO.findById(m.getId());
		if(obj.isEmpty()) {
			mav.addObject("msg", "회원가입에 실패하였습니다.");
			mav.setViewName("error");
		}*/
		
		
		return mav ;
	}
	
	
	
	@GetMapping("/member/login")
	public void loginForm() {	
		memberDAO.save(
				new Member("kim", 
				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("kim"),
				"김유신", 
				"user", 
				null));
		memberDAO.save(
				new Member("lee", 
				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("lee"), 
				"이순신", 
				"user", 
				null));
		
		memberDAO.save(
		new Member("sist01", 
		PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("sist01"), 
		"관리자1", 
		"admin", 
		null));
		
		memberDAO.save(
		new Member("sist02", 
		PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("sist02"), 
		"관리자2", 
		"admin", 
		null));
		
	}
	
	
//	@PostMapping("/member/login")
//	public ModelAndView loginSubmit(String id, String pwd, HttpSession session) {
//		String msg = "";
//		ModelAndView mav = new ModelAndView("redirect:/board/list/1");
//		Optional<Member> option = memberDAO.findById(id);
//		if(option.isPresent()) {
//			String dbPwd = option.get().getPwd();
//			if(pwd.equals(dbPwd)) {
//				session.setAttribute("id", id);				
//			}else {				
//				mav.setViewName("redirect:/member/login");
//			}
//		}else {			
//			mav.setViewName("redirect:/member/login");
//		}
//		
//		return mav;
//	}
}






