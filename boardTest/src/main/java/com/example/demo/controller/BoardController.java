package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.BoardDAO;

@Controller
public class BoardController {
	
	public int pageSize = 3;
	public int totalRecord = 0;
	public int totalPage = 1;
	
	public int pageGroup = 5;	// 페이지 그룹 정해주기	
	
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/listBoard")
	public void list(Model model,
			@RequestParam(value="pageNUM",defaultValue ="1") int pageNUM) {
		// 전체페이지와 전체레코드수 구하기
		totalRecord = dao.getTotalRecord();
		totalPage = (int) Math.ceil( totalRecord / (double)pageSize);
		int start = (pageNUM-1)*pageSize+1;
		int end = start + pageSize -1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		// 상태유지
		model.addAttribute("totalPage", totalPage); 
		model.addAttribute("list",dao.findAll(map));
		
		// 만약 현재페이지가 1,2,3,4,5 ===> 1,5
		// 만약 현재페이지가 6,7,8,9,10 ===> 6,10
		int startPage = (pageNUM-1)/pageGroup*pageGroup+1;
		int endPage = startPage+pageGroup-1;
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
	}
	
	@GetMapping("/detailBoard")
	public void detail(Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
}
