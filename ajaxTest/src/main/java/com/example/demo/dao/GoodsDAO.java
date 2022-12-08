package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.GoodsVO;

@Repository
public class GoodsDAO {
	
	public List<GoodsVO> listGoods(){
		return DBManager.listGoods();
	}
	public GoodsVO findByNo(int no) {
		return DBManager.findByNo(no);
	}
	public int insertGoods(GoodsVO g) {
		return DBManager.insertGoods(g);
	}
}
