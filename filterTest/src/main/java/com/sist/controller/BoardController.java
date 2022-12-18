package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

	@RequestMapping(value= "/list.do", produces="text/json;charset=utf-8")
	@ResponseBody
	public String list() {
		return "list";
	}
	
	@RequestMapping(value= "/insert.do", produces="text/json;charset=utf-8")
	@ResponseBody
	public String insert() {
		return "insert";
	}
}
