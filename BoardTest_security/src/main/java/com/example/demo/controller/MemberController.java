package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Controller
@Setter
public class MemberController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/member/login")
	public void loginForm() {	
//		memberDAO.save(
//				new Member("kim", 
//				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("kim"), 
//				"kim", 
//				"user", 
//				null));
//		memberDAO.save(
//				new Member("lee", 
//				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("lee"), 
//				"lee", 
//				"user", 
//				null));
//		memberDAO.save(
//				new Member("sist1", 
//				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("sist1"), 
//				"관리자", 
//				"admin", 
//				null));
//		memberDAO.save(
//				new Member("sist2", 
//						PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("sist2"), 
//						"관리자2", 
//						"admin", 
//						null));
	}
	
	
	@GetMapping("/member/join")
	public void joinForm() {
	}
	@PostMapping("/member/join")
	public ModelAndView joinSubmit(Member m) {
		ModelAndView mav = new ModelAndView("redirect:/member/login");
		String ppwd = passwordEncoder.encode(m.getPwd());
		m.setPwd(ppwd);
		memberDAO.save(m);
		// 가입이 실패했다면 (추가를 했는데 성공햇나 실패햇나 판단하기위해 사용한다
		// findById은 optional를 반환하기때문에 사용
		// try catch를 사용해도 된다
		Optional<Member> obj = memberDAO.findById(m.getId());
		if(obj.isEmpty()) {
			mav.addObject("msg","회원가입 실패");
			mav.setViewName("error");
		}
		
		return mav;
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






