<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
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
	<table border=1 width="60%">
		<tr>
			<th>상품번호</th>
			<th>상품이름</th>
			<th>상품가격</th>
			<th>상품수량</th>
			<th>상품사진</th>
		</tr>
		<c:forEach var="b" items="${list }">
			<tr>
				<td>${b.no }</td>
				<td><a href="detailGoods?no=${b.no }">${b.name }</a></td>
				<td>${b.price }</td>
				<td>${b.qty }</td>
				<td>${b.fname }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>