package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.View_ListOrdersVO;

@Repository
public interface View_ListOrdersDAO extends JpaRepository<View_ListOrdersVO, Integer> {
	
	//public List<View_ListOrdersVO> findByName(String name);
	
	@Query("select * from View_ListOrders v where v.name=?1")
	public List<View_ListOrdersVO> searchName(String name);
}
