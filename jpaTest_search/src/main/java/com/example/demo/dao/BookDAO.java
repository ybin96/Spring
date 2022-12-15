package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BookVO;

@Repository
public interface BookDAO extends JpaRepository<BookVO, Integer> {
	
	// 도서명으로 검색하는 메소드 추가
	public List<BookVO> findByBookname(String bookname);
	
	// 도서명으로 like 연산을 수행하는 메소드 추가
	public List<BookVO> findByBooknameLike(String bookname);
	
	// like에 %를 포함하는 메소드
	public List<BookVO> findByBooknameContaining(String bookname);
	
	public List<BookVO> findByBookid(int bookid);
	public List<BookVO> findByPrice(int price);
	public List<BookVO> findByPublisherContaining(String publisher);
	
}
