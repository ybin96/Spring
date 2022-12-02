package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.GoodsVO;

@Repository
public class GoodsDAO {
	
	public List<GoodsVO> findAll(HashMap<String, Object> map){
		return DBManager.findAll(map);
	}
	
	public int insert(GoodsVO g) {
		return DBManager.insert(g);
	}
	
	public GoodsVO findByNo(int no) {
		return DBManager.findByNo(no);
	}
	
	public int update(GoodsVO g) {
		return DBManager.update(g);
	}
	
	public int delete(int no) {
		return DBManager.delete(no);
	}
	
	public int getTotal(HashMap<String, Object> map2) {
		return DBManager.getTotal(map2);
	}
}














