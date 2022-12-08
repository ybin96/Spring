package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.DeptVO;
import com.example.demo.vo.EmpVO;
import com.example.demo.vo.GoodsVO;

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
	
	public static List<EmpVO> findAll(){
		SqlSession session = sqlSessionFactory.openSession();
		List<EmpVO> list = session.selectList("emp.findAll");
		session.close();
		return list;
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
	
	public static List<GoodsVO> listGoods(){
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("goods.findAll");
		session.close();
		return list;
	}
	
	public static GoodsVO findByNo(int no){
		GoodsVO g = null;
		SqlSession session = sqlSessionFactory.openSession();
		g = session.selectOne("goods.findByNo",no);
		session.close();
		return g;
	}
	
	public static int insertGoods(GoodsVO g) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("goods.insert",g);
		session.commit();
		session.close();
		return re;
	}
}
