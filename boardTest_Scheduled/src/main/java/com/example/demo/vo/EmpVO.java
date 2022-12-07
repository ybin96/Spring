package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class EmpVO {
	private int eno;
	private String ename;
	private int dno;
	private int salary;
	private int comm;
	private Date hiredate;
	private String addr;
	private int mgr;
	private String job;
	private String email;
	private String jumin;
}
