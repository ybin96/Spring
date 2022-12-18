package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

	@RequestMapping(value= "/admin/menu1.do", produces="text/json;charset=utf-8")
	@ResponseBody
	public String menu1() {
		return "menu1";
	}
	
	@RequestMapping(value= "/admin/menu2.do", produces="text/json;charset=utf-8")
	@ResponseBody
	public String menu2() {
		return "menu2";
	}
}
