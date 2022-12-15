package com.example.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="view_listorders")
public class View_ListOrdersVO {
	@Id
	private int orderid;
	
	private String name;
	private String bookname;
	private int saleprice;
	private int price;
	private String orderdate;
}
