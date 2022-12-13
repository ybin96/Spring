package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	
	@GetMapping("/member/a")
	public String a() {
		return "member a";
	}
	@GetMapping("/member/b")
	public String b() {
		return "member b";
	}
}
