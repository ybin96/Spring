package com.example.demo.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class AdminController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/admin/index")
	public void index() {		
	}
	
	
	@GetMapping("/admin/member/list")
	public void memberList(Model model) {
		model.addAttribute("list", memberDAO.findAll());
	}
	
	
	
	@GetMapping("/admin/member/delete/{id}")
	public ModelAndView deleteMember(@PathVariable String id, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/images");
		
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		
		//삭제하기 전에 이 계정이 등록한 게시물의 파일이름들을 리스트에 미리 담아둡니다.
//		List<String> fname_list = boardDAO.findFnameById(id);
//		List<String> fname_list = boardDAO.findFnameByMember_id(id);
		List<Board> fname_list = boardDAO.findFnameByMember_id(id);
//		for(String fname:fname_list) {
//			System.out.println(fname);
//		}
		
		memberDAO.deleteById(id);
		
		Optional<Member> obj = memberDAO.findById(id);
		if(obj.isEmpty()) {
			for(Board b:fname_list) {
				if(b.getFname() != null && !b.getFname().equals("")) {
					File file = new File(path + "/" + b.getFname());
					file.delete();
				}
			}
		}
		
		
//		if(obj.isEmpty()) {
//			for(String fname:fname_list) {
//				if(fname != null && !fname.equals("")) {
//					File file = new File(path + "/" +fname);
//					file.delete();
//				}
//			}
//		}
		
		return mav;
	}
	
	
	
	
	@GetMapping("/admin/member/update/{id}")
	public ModelAndView updateMemberForm(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("/admin/member/update");
		mav.addObject("m", memberDAO.findById(id).get()) ;
		return mav;
	}
	
	@PostMapping("/admin/member/update")
	public ModelAndView updateMemberSubmit(Member m, String update_pwd) {
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		if(update_pwd != null && !update_pwd.equals("")) {
			m.setPwd(passwordEncoder.encode(update_pwd));
		}
		memberDAO.save(m);
		return mav;
	}
	
}















