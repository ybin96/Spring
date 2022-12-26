package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.BookVO;
import com.google.gson.Gson;

@RestController
public class BookController {

	@GetMapping("/book/list")
	public String list(){
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		list.add(new BookVO(1, "재미있는 자바", 30000, "쌍용미디어"));
		list.add(new BookVO(2, "신나는 스프링", 40000, "한빛미디어"));
		
		Gson gson = new Gson();
		
		String result = "sist("+ gson.toJson(list)  +")";
		return result;

	}
}
