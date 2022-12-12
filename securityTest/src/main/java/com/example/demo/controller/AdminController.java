package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin/menu1")
	public void admin1() {
	}
	@GetMapping("/admin/menu2")
	public void admin2() {
	}
}
