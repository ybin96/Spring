package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsDAO dao;

	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/listGoods")
	public void list(Model model) {
		model.addAttribute("list",dao.findAll());
	}
	 /*
	@GetMapping("/listGoods")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.findAll());
		return mav;
	}*/
	
	@GetMapping("/detailGoods")
	public void detail(Model model, int no) {
		model.addAttribute("g", dao.findNo(no));
	}
	
	/*
	@GetMapping("/detailGoods")
	public ModelAndView detail(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("g", dao.findNo(no));
		return mav;
	}*/
	
	@GetMapping("/insertGoods")
	public void insertForm() {
	}
	
	@PostMapping("/insertGoods")
	public ModelAndView insertSubmit(GoodsVO g) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		int re = dao.insert(g);
		if(re <= 0) {
			mav.addObject("msg", "상품등록에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
	
	/*
	@GetMapping("/updateGoods")
	public ModelAndView updateForm(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("g", dao.findNo(no));
		return mav;
	}
	
	@PostMapping("/updateGoods")
	public ModelAndView updateSubmit(GoodsVO g) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		int re = dao.update(g);
		if(re <= 0) {
			mav.addObject("msg", "상품수정에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}*/
	
	@GetMapping("/deleteGoods")
	public ModelAndView delete(int no) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		int re = dao.delete(no);
		if(re <= 0) {
			mav.addObject("msg", "상품삭제에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
}
