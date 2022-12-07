package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.EmpVO;
import com.example.demo.vo.MemberVO;

public class DBManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static List<BoardVO> findAll(HashMap<String, Object> map){
		SqlSession session = sqlSessionFactory.openSession();
		List<BoardVO> list = session.selectList("board.findAll",map);
		session.close();
		return list;
	}
	
	public static int getNextNo() {
		int no = 0;
		SqlSession session = sqlSessionFactory.openSession();
		no = session.selectOne("board.getNextNo");
		session.close();
		return no;
	}
	
	public static int insert(BoardVO b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("board.insert",b);
		session.commit();
		session.close();
		return re;
	}
	
	public static BoardVO findByNo(int no) {
		BoardVO b = null;
		SqlSession session = sqlSessionFactory.openSession();
		b = session.selectOne("board.findByNo", no);
		session.close();
		return b;
	}

	public static int update(BoardVO b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("board.update", b);
		session.commit();
		session.close();
		return re;
	}
	
	public static int delete(HashMap<String, Object> map) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.delete("board.delete",map);
		session.commit();
		session.close();
		return re;
	}

	public static void updateStep(int b_ref, int b_step) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_ref", b_ref);
		map.put("b_step", b_step);
		SqlSession session = sqlSessionFactory.openSession();
		session.update("board.updateStep",map);
		session.commit();
		session.close();
	}
	
	public static int getTotalRecord() {
		int cnt = 0;
		SqlSession session = sqlSessionFactory.openSession();
		cnt = session.selectOne("board.totalRecord");
		return cnt;
	}
	
	public static int insertMember(MemberVO m) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("member.insert", m);
		session.commit();
		session.close();
		return re;
	}
	
	public static boolean isMember(HashMap<String, Object> map) {
		boolean re = false;
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO m =session.selectOne("member.isMember", map);
		if(m != null) {
			re = true;
		}
		session.close();
		return re;
	}

	public static MemberVO findById(String id) {
		MemberVO m = null;
		SqlSession session = sqlSessionFactory.openSession();
		m = session.selectOne("member.findById",id);
		session.close();
		return m;
	}
	
	public static List<EmpVO> listEmp(){
		List<EmpVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("emp.findAll");
		session.close();
		return list;
	}
	
}
