package com.example.demo.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;

@RestController
@Setter
public class HtmlMailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/sendMailHtml")
	public String sendMailHtml() {
		
		mailSender.send(new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				String str = "<h2>회원가입 성공<h2>";
				str += "<img src='cid:ball'/>";
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
				helper.setFrom("samuel6264@gmail.com");
				helper.setTo("samuel9634@naver.com");
				helper.setSubject("html형태의 이메일");
				helper.setText(str, true);
				
				helper.addInline("ball", new ClassPathResource("img/rocket.png"));
				helper.addAttachment("hello.txt", new ClassPathResource("doc/hello.txt"));
			}
		});
		
		return "ok";
	}
}
