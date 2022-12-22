package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;

import lombok.Setter;

@Service
@Setter
public class BoardService {
	
	@Autowired
	private BoardDAO dao;
	
//	public void save(Board b) {
//		dao.save(b);
//	}
	
	public int getNextNo() {		
		return dao.getNextNo();
	}
	
	public void insert(Board b) {
		dao.insert(b);
	}
	
	public List<Board> findAll(){
		return dao.findAll();
	}
	
	public Board findById(int no) {
		return dao.findById(no).get();
	}
	
	
	public void updateStep(int b_ref, int b_step) {
		dao.updateStep(b_ref, b_step);
	}
	
	
//	public List<Board> findAllByOrderByB_refDescB_stepAsc(){
//		return dao.findAllByOrderByB_refDescB_stepAsc();
//	}
	
	public List<Board> selectAll(int start, int end){		
		return dao.selectAll(start, end);
	}
	
	public List<Board> selectAllById(int start, int end, String id){		
		return dao.selectAllById(start, end, id);
	}
	
	public int deleteBoard(int no, String pwd) {
		return dao.deleteBoard(no, pwd);
	}
	
	public int updateBoard(Board b) {
		return dao.updateBoard(b);
	}
}















