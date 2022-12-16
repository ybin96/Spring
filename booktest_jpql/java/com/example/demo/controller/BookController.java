package com.example.demo.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.BookService;
import com.example.demo.vo.BookVO;

import lombok.Setter;

@Controller
@Setter
public class BookController {

	@Autowired
	private BookService bs;
	
	@GetMapping("/book/insert")
	public void insert() {		
	}
	
	
	@RequestMapping("/book/list")
	public void list(Model model, String keyword, String cname, HttpServletRequest request) {
		if(request.getMethod().equals("GET")) {
			model.addAttribute("list", bs.findAll());
		}else {
			//model.addAttribute("list", bs.findByBookname(bookname));
			//System.out.println("검색어:"+bookname);
			//model.addAttribute("list", bs.findByBooknameLike(bookname));
			//model.addAttribute("list", bs.findByBooknameContaining(bookname));
			List<BookVO> list = bs.search(keyword, cname);
			model.addAttribute("list", list);
		}
	}
	
	@PostMapping("/book/save")
	public ModelAndView save(BookVO b) {
		ModelAndView mav = new ModelAndView("redirect:/book/list");
		bs.save(b);
		return mav;
	}
	
	
	
	@GetMapping("/book/update/{bookid}")
	public ModelAndView update(@PathVariable int bookid) {
		ModelAndView mav = new ModelAndView("/book/update");
		mav.addObject("b", bs.getOne(bookid));
		return mav;
	}
	
	@GetMapping("/book/delete/{bookid}")
	public ModelAndView delete(@PathVariable int bookid) {
		ModelAndView mav = new ModelAndView("redirect:/book/list");
		bs.deleteById(bookid);
		return mav;
	}
}













