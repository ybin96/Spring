package com.example.demo.schedule;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import lombok.Setter;

@Component
@EnableScheduling
@Setter
public class EmailSchedule {
	@Autowired
	private EmpDAO dao;
	@Autowired
	private JavaMailSender sender;
	
	// 초 분 시 일 월 요일 년도
	//@Scheduled(cron="0 26 10 8 * ?")
	public void sendEmail() {
		// 오늘 날짜를 가져온다
		
		List<EmpVO> list = dao.findAll();
		for(EmpVO e:list) {
			String from ="samuel6264@gmail.com";
			String to = e.getEmail();
			String str = SistUtil.getHtml(e);	// HTML은 따로 메소드를 만들어서 진행
			
			sender.send(new MimeMessagePreparator() {
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
					message.setFrom(from);
					message.setTo(to);
					message.setSubject("급여명세서");
					message.setText(str,true);
					
				}
			});
		}
	}
}
