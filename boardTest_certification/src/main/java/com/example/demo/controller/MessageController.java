package com.example.demo.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.youiwe.webservice.BitSms;

@RestController
//responsebody + controller
public class MessageController {

	@GetMapping("/sendMsg")
	public String sendMsg() {	
		String from = "01025598279";	// 보내는 사람 전화번호
		String to =	"01066101435";		// 받는 사람 전하번호
		String msg = "카카오프렌즈~";		// 보낼 메세지
		
		BitSms sms = new BitSms();		// copy한 BitSms 객체 생성
		sms.sendMsg(from, to, msg);
		
		return "OK";
	}
	
	@GetMapping("/sendMsgCode")
	public String sendMsgCode(String phone) {
		String code = "";
		
		Random r = new Random();
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		
		String from = "01025598279";	// 보내는 사람 전화번호
		String to =	phone;		// 받는 사람 전화번호
		String msg = code;		// 보낼 메세지
		
		BitSms sms = new BitSms();		// copy한 BitSms 객체 생성
		sms.sendMsg(from, to, msg);
		
		return code;
	}
}
