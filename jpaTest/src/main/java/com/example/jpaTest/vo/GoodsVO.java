package com.example.jpaTest.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="goods")	// table이름을 goods라고 정의한다.
public class GoodsVO {
	
	@Id		// primary key 설정
	private int no;
	
	private String name;
	private int price;
	private int qty;
	private String fname;
}
