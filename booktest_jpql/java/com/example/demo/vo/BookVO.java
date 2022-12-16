package com.example.demo.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class BookVO {
	
	@Id
	private int bookid;
	
	private String bookname;
	private String publisher;
	private int price;
}
