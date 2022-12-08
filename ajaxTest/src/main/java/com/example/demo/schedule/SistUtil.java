package com.example.demo.schedule;

import java.util.Date;

import com.example.demo.vo.EmpVO;

public class SistUtil {
	
	public static String getHtml(EmpVO e) {
		Date today = new Date();
		int year = today.getYear()+1900;
		int month = today.getMonth()+1;
		
		String str = "";
		str += "<h3>"+year+"년"+month+"월"+"<h3>";
		str += "<table border='1'>";
		str += "<tr>";
		str += "<td>사원번호</td>";
		str += "<td>이름</td>";
		str += "<td>부서번호</td>";
		str += "<td>입사일</td>";
		str += "<td>관리자번호</td>";
		str += "<td>기본금</td>";
		str += "<td>수당</td>";
		str += "<td>실수령액</td>";
		str += "</tr>";
		str += "<tr>";
		str += "<td>"+e.getEno()+"</td>";
		str += "<td>"+e.getEname()+"</td>";
		str += "<td>"+e.getDno()+"</td>";
		str += "<td>"+e.getHiredate()+"</td>";
		str += "<td>"+e.getMgr()+"</td>";
		str += "<td>"+e.getSalary()+"</td>";
		str += "<td>"+e.getComm()+"</td>";
		str += "<td>"+(e.getSalary()+e.getComm())+"</td>";
		str += "</tr>";
		str += "</table>";
		
		return str;
	}
}
