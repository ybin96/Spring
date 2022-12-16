package com.example.demo.service;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

import lombok.Setter;

@Service
@Setter
public class BookService {
	
	@Autowired
	private BookDAO dao;
	
	public List<BookVO> findByBookname(String bookname){
		return dao.findByBookname(bookname);
	}
	
	public List<BookVO> findByBooknameLike(String bookname){
		System.out.println("BookService bookname:"+bookname);
		return dao.findByBooknameLike(bookname);
	}
	
	public List<BookVO> findByBooknameContaining(String bookname){
		return dao.findByBooknameContaining(bookname);
	}
	
	public List<BookVO> findAll(){
		return dao.findAll();
	}
	
	public void save(BookVO b) {
		dao.save(b);
	}
	
	public BookVO getOne(int bookid) {
		return dao.getOne(bookid);
	}
	
	public void deleteById(int bookid) {
		dao.deleteById(bookid);
	}

	public List<BookVO> search(String keyword, String cname) {
		// TODO Auto-generated method stub
		List<BookVO> list = null;
		switch(cname) {
			case "bookid":list = dao.findByBookid( Integer.parseInt(keyword)  );break;
			case "price":list = dao.findByPrice( Integer.parseInt(keyword)  );break;
			case "bookname":list = dao.findByBooknameContaining(keyword);break;
			case "publisher":list = dao.findByPublisherContaining(keyword);break;
		}
		return list;
	}
}














