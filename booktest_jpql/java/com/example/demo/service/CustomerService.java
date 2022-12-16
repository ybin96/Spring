package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

import lombok.Setter;

@Service
@Setter
public class CustomerService {
	@Autowired
	private CustomerDAO dao;
	
	public List<CustomerVO> findAll(){
		return dao.findAll();
	}
	
	public void save(CustomerVO c) {
		dao.save(c);
	}
	
	public Optional<CustomerVO> findById(int custid) {	
		return dao.findById(custid);
	}
	
	public void deleteById(int custid) {
		dao.deleteById(custid);
	}
}












