package com.example.demo.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import lombok.Setter;

@Component
@EnableScheduling
@Setter
public class SistUtil {
	
	@Autowired
	private MailSender mailSender;
	@Autowired
	private EmpDAO dao;
	
	// 초 분 시간 일 월 요일 연도
	//@Scheduled(fixedRate = 10000)
	@Scheduled(cron = "0 42 14 7 * ?")
	public void send() {
		List<EmpVO> list = dao.findAll();
		for(EmpVO e:list) {
			String to = e.getEmail();
			String name = e.getEname();
			int total = e.getSalary()+e.getComm();
			String msg = name+"님 이번달 급여는 "+total+"원입니다.";
			
			try {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setFrom("samuel6264@gmail.com");	
				mailMessage.setTo(to);					
				mailMessage.setSubject("급여명세서");		
				mailMessage.setText(msg);				
				mailSender.send(mailMessage);
			} catch (Exception ex) {
				System.out.println("예외발생:"+ex.getMessage());
			}
					
		}
	}
	
}
