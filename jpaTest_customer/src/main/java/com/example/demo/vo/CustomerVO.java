package com.example.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="customer")
public class CustomerVO {
	
	@Id
	private int custid;
	
	private String name;
	private String address;
	private String phone;
}
