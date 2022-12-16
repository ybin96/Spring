package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.View_ListOrdersDAO;
import com.example.demo.vo.View_ListOrders;

import lombok.Setter;

@Service
public class View_ListOrdersService {

	
	public List<View_ListOrders> findByNameOrderByOrderid(String name, View_ListOrdersDAO dao){
		return dao.findByNameOrderByOrderid(name);
	}
	
	
	public List<View_ListOrders> findByNameOrderByName(String name, View_ListOrdersDAO dao){
		return dao.findByNameOrderByName(name);
	}
	public List<View_ListOrders> findByNameOrderByBookname(String name, View_ListOrdersDAO dao){
		return dao.findByNameOrderByBookname(name);
	}
	public List<View_ListOrders> findByNameOrderByOrderdate(String name, View_ListOrdersDAO dao){
		return dao.findByNameOrderByOrderdate(name);
	}
	public List<View_ListOrders> findByNameOrderBySaleprice(String name, View_ListOrdersDAO dao){
		return dao.findByNameOrderBySaleprice(name);
	}
	public List<View_ListOrders> findByNameOrderByPrice(String name, View_ListOrdersDAO dao){
		return dao.findByNameOrderByPrice(name);
	}
	
	public List<View_ListOrders> findByBooknameOrderByOrderid(String bookname, View_ListOrdersDAO dao){
		return dao.findByBooknameOrderByOrderid(bookname);
	}
	public List<View_ListOrders> findByBooknameOrderByName(String bookname, View_ListOrdersDAO dao){
		return dao.findByBooknameOrderByName(bookname);
	}
	public List<View_ListOrders> findByBooknameOrderByBookname(String bookname, View_ListOrdersDAO dao){
		return dao.findByBooknameOrderByBookname(bookname);
	}
	public List<View_ListOrders> findByBooknameOrderByOrderdate(String bookname, View_ListOrdersDAO dao){
		return dao.findByBooknameOrderByOrderdate(bookname);
	}
	public List<View_ListOrders> findByBooknameOrderBySaleprice(String bookname, View_ListOrdersDAO dao){
		return dao.findByBooknameOrderBySaleprice(bookname);
	}
	public List<View_ListOrders> findByBooknameOrderByPrice(String bookname, View_ListOrdersDAO dao){
		return dao.findByBooknameOrderByPrice(bookname);
	}
		
	
	public List<View_ListOrders> findAllByOrderByOrderid(View_ListOrdersDAO dao){
		System.out.println("서비스 동작함."+dao);
		return dao.findAllByOrderByOrderid();
	}
	
	public List<View_ListOrders> findAllByOrderByName(View_ListOrdersDAO dao){
		return dao.findAllByOrderByName();
	}
	
	public List<View_ListOrders> findAllByOrderByBookname(View_ListOrdersDAO dao){
		return dao.findAllByOrderByBookname();
	}
	
	public List<View_ListOrders> findAllByOrderByOrderdate(View_ListOrdersDAO dao){
		return dao.findAllByOrderByOrderdate();
	}
	
	public List<View_ListOrders> findAllByOrderBySaleprice(View_ListOrdersDAO dao){
		return dao.findAllByOrderByOrderdate();
	}
	
	public List<View_ListOrders> findAllByOrderByPrice(View_ListOrdersDAO dao){
		return dao.findAllByOrderByPrice();
	}
	
}
