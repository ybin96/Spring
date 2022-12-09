package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.DeptVO;

public class DBManager {
	
	static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static List<DeptVO> listDept(){
		SqlSession session = sqlSessionFactory.openSession();
		List<DeptVO> list = session.selectList("dept.findAll");
		session.close();
		return list;
	}
	
	public static int insertDept(DeptVO d) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("dept.insert",d);
		session.commit();
		session.close();
		return re;
	}
	
	public static int updateDept(DeptVO d) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("dept.update", d);
		session.commit();
		session.close();
		return re;
	}
	
	public static int deleteDept(int no) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.delete("dept.delete", no);
		session.commit();
		session.close();
		return re;
	}
	
}
