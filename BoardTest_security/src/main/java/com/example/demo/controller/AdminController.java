package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Controller
@Setter
public class AdminController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/admin/index")
	public void index() {
	}
	
	@GetMapping("/admin/member/list")
	public void list(Model model) {
		model.addAttribute("list",memberDAO.findAll());
	}
	
	@GetMapping("/admin/member/insert")
	public void insertForm() {
	}
	
	@PostMapping("/admin/member/insert")
	public ModelAndView insertSubmit(Member m,String select_role) {
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		m.setRole(select_role);
		memberDAO.save(m);
		Optional<Member> obj = memberDAO.findById(m.getId());
		if(obj.isEmpty()) {
			mav.addObject("msg","관리자모드에서 회원등록을 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/admin/member/delete/{id}")
	public ModelAndView deleteForm(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		memberDAO.deleteById(id);
		return mav;
	}
	
	@GetMapping("/admin/member/update/{id}")
	public ModelAndView updateForm(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("/admin/member/update");
		mav.addObject("m",memberDAO.findById(id).get());
		return mav;
	}
	
	@PostMapping("/admin/member/update")
	public ModelAndView updateSubmit(Member m, String select_role, String update_pwd) {
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		if(update_pwd != null && !update_pwd.equals("")) {
			m.setPwd(passwordEncoder.encode(update_pwd));
		}
		m.setRole(select_role);
		memberDAO.save(m);
		return mav;
	}
}






