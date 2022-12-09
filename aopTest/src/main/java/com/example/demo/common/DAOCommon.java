package com.example.demo.common;

import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DAOCommon {
	
	@Pointcut("execution(public * com.example.demo.dao..*(..))")
	public void daoCommon() {}
	
	/*
	@AfterReturning(pointcut="daoCommon",returning = "ret")
	public void afterRetunring(JoinPoint joinPoint, Object ret) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("------------------------------");
		System.out.println("타깃메소드("+methodName+")가 정상 완료되었습니다.");
		System.out.println("반환값:"+ret);
		System.out.println("------------------------------");
	}
	
	/*
	@AfterThrowing(pointcut="execution(public * com.example.demo.dao..*(..))",throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("------------------------------");
		System.out.println("타깃메소드("+methodName+")가 비정상 완료되었습니다.");
		System.out.println("예외:"+ex.getMessage());
		System.out.println("------------------------------");
	}
	
	/*
	@After("daoCommon()")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("타깃메소드("+methodName+")가 완료되었습니다.");
	}
	
	/*
	@AfterThrowing("daoCommon()")
	public void afterThrowing(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("타깃 메소드("+methodName+") 비정상 완료되었습니다.");
	}
	
	/*
	@AfterReturning("daoCommon()")
	public void afterRetruning(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("타깃 메소드("+methodName+")가 정상 완료되었습니다.");
	}
	
	/*
	@Around("daoCommon()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object obj = null;
		String methodName = joinPoint.getSignature().getName();
		System.out.println(methodName+"가 동작하기 전입니다.");
		long start = System.currentTimeMillis();
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(methodName+"가 완료되었습니다.");
		System.out.println("걸린시간:"+(end-start));
		return obj;
	}
	
	/*
	@Before("daoCommon()")
	public void pro(JoinPoint joinpoint) {
		String methodName1 = joinpoint.getSignature().toLongString();
		// public java.util.List com.example.demo.dao.DeptDAO.listDept() 목록
		// public int com.example.demo.dao.DeptDAO.update(com.example.demo.vo.DeptVO) 수정
		String methodName2 = joinpoint.getSignature().toShortString();
		// DeptDAO.listDept() 목록
		// DeptDAO.update(..) 수정
		System.out.println(methodName1);
		System.out.println(methodName2);
		
		Object targetobj = joinpoint.getTarget();
		System.out.println("타깃객체:"+targetobj);
		
		Object []args = joinpoint.getArgs();
		System.out.println("매개변수 목록");
		if(args != null) {
			for(Object obj : args) {
				System.out.println(obj);
			}
		}
		System.out.println("DAO가 처리되기전 동작하는 공통기능");
		System.out.println("==========================================");
	}
	*/
}
