package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@GetMapping("/member/login")
	public void loginForm() {
	}
	
	@PostMapping("/member/login")
	public ModelAndView loginSubmit(String id, String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/board/list/1");
		// findById가 optional를 반환한다
		Optional<Member> option = dao.findById(id);
		if(option.isPresent()) {
			// db pwd와 사용자 pwd가 같은지 확인
			// dbPwd = db에 저장되어있는 암호
			String dbPwd = option.get().getPwd();
			if(pwd.equals(dbPwd)) {
				// session으로 상태유지
				session.setAttribute("id", id);
			}else {
				mav.setViewName("redirect:/member/login");	
			}
		}else {
			mav.setViewName("redirect:/member/login");
		}
		return mav;
	}
}
