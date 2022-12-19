package com.example.demo.controller;


import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/board/insert/{no}")
	public ModelAndView insertForm( @PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/insert");
		mav.addObject("no", no);
		return mav;
	}
	
	@PostMapping("/board/insert")
	public ModelAndView insertSubmit(Board b, HttpServletRequest request) {
		
		//일단 새글이라고 봅니다.
		int no = boardService.getNextNo();
		int b_ref = no;
		int b_step = 0;
		int b_level = 0;
		
		//만약에 답글이라면
		int pno = b.getNo();   //부모글번호 ==> 0:새글, 0!= 답글
		if(pno != 0) {
			Board p = boardService.findById(pno);
			b_ref = p.getB_ref();
			b_step = p.getB_step();
			b_level = p.getB_level();
			boardService.updateStep(b_ref, b_step);
			b_step++;
			b_level++;
		}
		
		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_step(b_step);
		b.setB_level(b_level);
				
		MultipartFile uploadFile = b.getUploadFile();
		String fname;
		fname = uploadFile.getOriginalFilename();
		
		String path = request.getServletContext().getRealPath("images");
		if(fname != null && !fname.equals("")) {
			try {
				byte []data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}else {
			fname="";
		}
		
		b.setFname(fname);
		
		String ip = request.getRemoteAddr();
		b.setIp(ip);
		ModelAndView mav = new ModelAndView("redirect:/board/list");	
		boardService.insert(b);
		return mav;
	}
	
	@GetMapping("/board/list")
	public void list(Model model) {
		//model.addAttribute("list", boardService.findAll());
		//model.addAttribute("list",boardService.findAllByOrderByB_refDescOrderByB_stepAsc());
		model.addAttribute("list",boardService.selectAll());
	}
	
	
	@GetMapping("/board/detail/{no}")
	public ModelAndView detail(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/detail");
		mav.addObject("b", boardService.findById(no));
		return mav;
	}
	
	
}


















