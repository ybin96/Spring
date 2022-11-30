package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

@Controller
@RequestMapping("/updateGoods")
public class UpdateGoodsController {
	
	@Autowired
	private GoodsDAO dao;
	
	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void form(Model model, int no) {
		model.addAttribute("g", dao.findNo(no));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(GoodsVO g) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		int re = dao.update(g);
		if(re <= 0) {
			mav.addObject("msg", "수정에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
}
