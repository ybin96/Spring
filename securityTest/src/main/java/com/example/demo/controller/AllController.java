package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllController {

	@GetMapping("/all/menu1")
	public void menu1() {
	}
	@GetMapping("/all/menu2")
	public void menu2() {
	}
}
