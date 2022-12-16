package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BookVO;

@Repository
public interface BookDAO extends JpaRepository<BookVO, Integer> {
	
	//도서명으로 검색하는 메소드 추가
	public List<BookVO> findByBookname(String bookname);
	
	// 도서명으로 like연산을 수행하는 메소드 추가
	// %를 포함하지 않아요.
	public List<BookVO> findByBooknameLike(String bookname);
	
	// like에 %를 포함합니다.
	public List<BookVO> findByBooknameContaining(String bookname);
	
	// 도서번호로 검색하는 기능
	public List<BookVO> findByBookid(int bookid);
	
	// 도서가격으로 검색하는 기능
	public List<BookVO> findByPrice(int price);
	
	//출판이름으로 검색하는 기능
	public List<BookVO> findByPublisherContaining(String publisher);
	
}
