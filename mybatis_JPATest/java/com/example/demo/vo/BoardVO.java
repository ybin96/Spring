package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int no;
	private int b_level;
	private int b_ref;
	private int b_step;
	private String content;
	private String fname;
	private int hit;
	private String ip;
	private String pwd;
	private Date regdate;
	private String title;
	private String id;
}
