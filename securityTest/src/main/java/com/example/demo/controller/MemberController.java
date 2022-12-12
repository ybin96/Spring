package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import lombok.Setter;

@Controller
@Setter
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public void login() {
	}
	
	@GetMapping("/join")
	public void joinForm() {
	}
	
	@PostMapping("/join")
	public ModelAndView joinSubmit(MemberVO m) {
		ModelAndView mav = new ModelAndView("redirect:/login");
		m.setPwd(passwordEncoder.encode(m.getPwd())); // 암호를 다시암호화한다.
		int re = -1;
		re = dao.insert(m);
		if(re != 1) {
			mav.setViewName("/all/error");
			mav.addObject("msg","회원가입에 실패");
		}
		return mav;
	}
	
	@GetMapping("/service1")
	public void service1(HttpSession session) {
		// 상태유지를 위해 시큐리티의 인증객체를 가져온다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// 인증객체를 통해서 로그인한 User객체를 받아온다
		User user = (User)authentication.getPrincipal();
		
		// 인증된 User를 통해서 로그인한 회원의 아이디를 가져온다
		String id = user.getUsername();
		
		// 회원정보를 세션에 상태유지한다.
		session.setAttribute("id", id);
	}
	
	@GetMapping("/service2")
	@ResponseBody
	public String service2() {
		return "서비스2입니다";
	}
}
