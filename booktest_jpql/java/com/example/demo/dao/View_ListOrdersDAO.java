package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.View_ListOrders;

@Repository
public interface View_ListOrdersDAO extends JpaRepository<View_ListOrders, Integer> {
//	public List<View_ListOrders> findByName(String name);
	
	@Query("select v from View_ListOrders v where v.name=?1")
	public List<View_ListOrders> searchName(String name);
	
	//검색칼럼
	static final String CASE_WEHN = "\nCASE\n"			
			+" WHEN :search_column = 'name' THEN v.name\n"
			+" WHEN :search_column = 'bookname' THEN v.bookname\n"
			+" ELSE v.name\n"
			+"END\n";
	
	//정렬칼럼_문자
	static final String CASE_SORT = "\nCASE\n"			
			+" WHEN :sort_column = 'name' THEN v.name\n"
			+" WHEN :sort_column = 'bookname' THEN v.bookname\n"
			+" WHEN :sort_column = 'orderdate' THEN v.orderdate\n"
			+" ELSE v.name\n"
			+"END\n";
	
	
	//정렬칼럼_숫자
	static final String CASE_SORT_NUM = "\nCASE\n"			
			+" WHEN :sort_column = 'orderid' THEN v.orderid\n"
			+" WHEN :sort_column = 'saleprice' THEN v.saleprice\n"
			+" WHEN :sort_column = 'price' THEN v.price\n"
			+" ELSE v.orderid\n"
			+"END\n";
	
//	@Query("select v from View_ListOrders v where "+CASE_WEHN+" = :keyword order by " + CASE_SORT )
//	public List<View_ListOrders> find(String search_column, String keyword, String sort_column);
	
	
	//검색X 문자정렬
	@Query("select v from View_ListOrders v order by " + CASE_SORT)
	public List<View_ListOrders> findCharSort(String sort_column);
	
	//검색X 숫자정렬
	@Query("select v from View_ListOrders v order by " + CASE_SORT_NUM)
	public List<View_ListOrders> findNumSort(String sort_column);
	
	//검색O 문자정렬
	@Query("select v from View_ListOrders v where "+CASE_WEHN+" = :keyword order by " + CASE_SORT )
	public List<View_ListOrders> findSearchCharSort(String search_column, String keyword, String sort_column);
	
	//검색O 숫자정렬
	@Query("select v from View_ListOrders v where "+CASE_WEHN+" = :keyword order by " + CASE_SORT_NUM )
	public List<View_ListOrders> findSearchNumSort(String search_column, String keyword, String sort_column);
	
	
//	@Query(nativeQuery = true, value = "select orderid, name, bookname, orderdate, saleprice, price from View_ListOrders order by "+CASE_WEHN)
//	@Query("select v from View_ListOrders v where "+CASE_WEHN+" = :keyword order by " + CASE_SORT )
//	public List<View_ListOrders> find(String search_column, String keyword, String sort_column);
	
	//findByNameOrderByOrderid
	public List<View_ListOrders> findByNameOrderByOrderid(String name);
	public List<View_ListOrders> findByNameOrderByName(String name);
	public List<View_ListOrders> findByNameOrderByBookname(String name);
	public List<View_ListOrders> findByNameOrderByOrderdate(String name);
	public List<View_ListOrders> findByNameOrderBySaleprice(String name);
	public List<View_ListOrders> findByNameOrderByPrice(String name);
	
	public List<View_ListOrders> findByBooknameOrderByOrderid(String bookname);
	public List<View_ListOrders> findByBooknameOrderByName(String bookname);
	public List<View_ListOrders> findByBooknameOrderByBookname(String bookname);
	public List<View_ListOrders> findByBooknameOrderByOrderdate(String bookname);
	public List<View_ListOrders> findByBooknameOrderBySaleprice(String bookname);
	public List<View_ListOrders> findByBooknameOrderByPrice(String bookname);
		
	
	public List<View_ListOrders> findAllByOrderByOrderid();
	public List<View_ListOrders> findAllByOrderByName();
	public List<View_ListOrders> findAllByOrderByBookname();
	public List<View_ListOrders> findAllByOrderByOrderdate();
	public List<View_ListOrders> findAllByOrderBySaleprice();
	public List<View_ListOrders> findAllByOrderByPrice();
	
}
