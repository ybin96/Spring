package com.example.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class OrdersVO {
	
	@Id
	private int orderid;
	
	@ManyToOne
	@JoinColumn(name = "custid", insertable = true, updatable = true)
	private CustomerVO customerVO;
	
	@ManyToOne
	@JoinColumn(name = "bookid", insertable = true, updatable = true)
	private BookVO bookVO;
	
	private int saleprice;
	private String orderdate;
}









