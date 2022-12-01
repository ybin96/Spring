<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>상품 목록</h2>
	<hr>
	<a href="insertGoods">상품등록</a><br>
	
	<form action="listGoods" method="get">
		상품이름 : <input type="search" name="keyword">
		<input type="submit" value="검색">
	</form>
	
	<table border="1" width="80%">
		<tr>
			<td><a href="listGoods?column=no">상품번호</a></td>
			<td><a href="listGoods?column=name">상품이름</a></td>
			<td><a href="listGoods?column=price">가격</a></td>
			<td><a href="listGoods?column=qty">수량</a></td>
			<td><a href="listGoods?column=fname">파일이름</a></td>
		</tr>
		
		<c:forEach var="g" items="${list }">
			<tr>
				<td>${g.no }</td>
				<td>${g.name }</td>
				<td>${g.price }</td>
				<td>${g.qty }</td>
				<td>${g.fname }</td>
			</tr>
		</c:forEach>
	</table>
	
	<hr>
	<c:forEach var="i"  begin="1" end="${totalPage }" >
		<a href="listGoods?pageNUM=${i }">${i }</a> &nbsp;&nbsp;
	</c:forEach>
	
	
</body>
</html>












