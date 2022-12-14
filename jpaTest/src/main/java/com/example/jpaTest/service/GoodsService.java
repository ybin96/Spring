package com.example.jpaTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpaTest.dao.GoodsDAO;
import com.example.jpaTest.vo.GoodsVO;

@Service
public class GoodsService {

	@Autowired
	private GoodsDAO dao;
	
	public List<GoodsVO> findAll(){
		return dao.findAll();
	}
	
	// pk가 이미 있으면 수정해주고 없으면 추가해준다
	public void save(GoodsVO g) {
		dao.save(g);
	}
	
	// 상세보기할때 pk를 매개변수로 받아서 해당 레코드를 반환하는 메소드 getOne
	public GoodsVO getOne(int no) {
		return dao.getOne(no);
	}
	
	public void delete(int no) {
		dao.deleteById(no);
	}
}
