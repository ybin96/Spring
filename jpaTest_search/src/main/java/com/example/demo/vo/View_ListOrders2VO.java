package com.example.demo.vo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="view_listorders2")
public class View_ListOrders2VO {
	
	@Embedded
	private OrdersViewID id;
	
	private String orderdate;
	private int saleprice;
	private int price;
}
