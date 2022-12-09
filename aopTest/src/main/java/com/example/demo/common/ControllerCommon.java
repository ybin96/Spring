package com.example.demo.common;

import java.io.FileWriter;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ControllerCommon {

	@Pointcut("execution(public * com.example.demo.controller..*(..))")
	public void commonController() {}
	
	@Around("commonController()")
	public Object pro(ProceedingJoinPoint joinPoint) {
		// 타깃 메소드의 매개변수들을 배열로 갖고 온다(모든 매개변수 목록을 가져온다)
		Object []args = joinPoint.getArgs();
		
		// 매개변수의 첫번쨰 요소를 갖고 오면 그것이 바로 request(args의 0번째)
		// controller에서 첫번째 매개변수를 request로 지정했다
		HttpServletRequest request = (HttpServletRequest)args[0];
		
		// ip주소를 가져온다
		String ip = request.getRemoteAddr();
		// uri를 가져온다
		String uri = request.getRequestURI();
		
		// 현재 날짜 설정
		Date today = new Date();
		int year = today.getYear()+1900;
		int month = today.getMonth()+1;
		int day = today.getDate();
		int hh = today.getHours();
		int mm = today.getMinutes();
		int ss = today.getSeconds();
		String time = year+"년"+month+"월"+day+"일"+hh+":"+mm+":"+ss;
		
		// 시작하는 시간
		long start = System.currentTimeMillis();
		
		Object ret = null;
		try {
			ret = joinPoint.proceed();
		} catch (Throwable e) {
			System.out.println("예외발생"+e.getMessage());
		}
		// 끝나는 시간
		long end = System.currentTimeMillis();
		
		// 걸린 시간 
		long delay = end-start;
		System.out.println(uri+"/"+ ip+"/"+delay+"/"+time);
		
		String line = uri+"/"+ ip+"/"+delay+"/"+time+"\n";
		try {
			FileWriter fw = new FileWriter("c:/sist/log.txt",true);
			fw.write(line);
			fw.close();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return ret;
	}
}
