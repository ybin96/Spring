package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

	@PostMapping("/payok")
	public String payok(String imp_uid,String merchant_uid,int paid_amount, String apply_num) {
		System.out.println("imp_uid:"+imp_uid);
		System.out.println("merchant_uid:"+merchant_uid);
		System.out.println("paid_amount:"+paid_amount);
		System.out.println("apply_num:"+apply_num);
		return "OK";
	}
}
