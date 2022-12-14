package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.CustomerVO;

public class DBManager {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("μμΈλ°μ:"+e.getMessage());
		}
	}
	
	public static List<CustomerVO> findAll(){
		SqlSession session = sqlSessionFactory.openSession();
		List<CustomerVO> list = session.selectList("customer.findAll");
		session.close();
		return list;
	}
	
	public static CustomerVO findCustid(int custid){
		SqlSession session = sqlSessionFactory.openSession();
		CustomerVO vo = session.selectOne("customer.findCustid",custid);
		session.close();
		return vo;
	}

	public static int insert(CustomerVO c) {
		SqlSession session = sqlSessionFactory.openSession();
		int re = session.insert("customer.insert", c);
		session.commit();
		session.close();
		return re;
	}

	public static int update(CustomerVO c) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = session.update("customer.update", c);
		session.close();
		return re;
	}	
	
	public static int delete(int custid) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = session.delete("customer.delete", custid);
		session.close();
		return re;
	}
}
