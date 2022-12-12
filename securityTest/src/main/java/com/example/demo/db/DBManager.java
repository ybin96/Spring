package com.example.demo.db;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVO;

public class DBManager {

	public static SqlSessionFactory sessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static int insert(MemberVO m) {
		int re = -1;
		SqlSession session = sessionFactory.openSession();
		re = session.insert("member.insert",m);
		session.commit();
		session.close();
		return re;
	}
	
	public static MemberVO findById(String id) {
		MemberVO m = null;
		SqlSession session = sessionFactory.openSession();
		m = session.selectOne("member.findById",id);
		session.close();
		return m;
	}
}
