package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.OrdersVO;

@Repository
public interface OrdersDAO extends JpaRepository<OrdersVO, Integer>{
	
	@Query("select nvl(max(orderid),0) +1 from OrdersVO")
	public int getNextNo();
	
	@Modifying
	@Query(value = "insert into orders o(o.orderid,o.custid,o.bookid,o.saleprice,o.orderdate) values(:#{#o.orderid},:#{#o.customerVO.custid}, :#{#o.bookVO.bookid}, :#{#o.saleprice},sysdate)", nativeQuery = true)
//	@Query("insert into OrdersVO o(o.orderid,o.custid,o.bookid,o.saleprice,o.orderdate) values(:#{#o.orderid},:#{#o.customerVO.custid}, :#{#o.bookVO.bookid}, :#{#o.saleprice},sysdate)")     오류발생
	@Transactional
	public void insert(@Param("o") OrdersVO o);
	
	
}

















