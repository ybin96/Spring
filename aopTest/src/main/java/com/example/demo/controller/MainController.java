package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView main(HttpServletRequest request) {
		return new ModelAndView("index");
	}
}
