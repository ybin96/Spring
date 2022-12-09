package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.DeptVO;


@Repository
public class DeptDAO {
	

	public List<DeptVO> listDept(){
		return DBManager.listDept();
	}
	
	public int insert(DeptVO d) {
		return DBManager.insertDept(d);
	}
	
	public int update(DeptVO d) {
		//int r = 4/0;
		return DBManager.updateDept(d);
	}
	
	public int delete(int dno) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return DBManager.deleteDept(dno);
	}
}
