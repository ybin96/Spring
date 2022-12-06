package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
	
	@Autowired
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}


	@RequestMapping("/mail")
	@ResponseBody
	public String mail() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("samuel6264@gmail.com");	// 보내는 사람
		mailMessage.setTo("samuel9634@naver.com");		// 받는 사람
		mailMessage.setSubject("메일보내기 연습");			// 메세지 제목
		mailMessage.setText("메일을 보냅니다!");				// 보내는 메세지
		
		try {
			mailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return "OK";
	}
	
	@RequestMapping("/sendCode")
	@ResponseBody
	public String sendCode(String email) {
		System.out.println("email:"+email);
		String code = "";
		// 코드 난수 4자리 만들기
		Random r = new Random();
		code += r.nextInt(10);	// 0~9 난수
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("samuel6264@gmail.com");	
		mailMessage.setTo(email);		
		mailMessage.setSubject("인증코드");			
		mailMessage.setText(code);				
		
		try {
			mailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return code;
	}
}
