<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>도서목록</h2>
	<c:forEach var="b" items="${list }">
		${b.bookid } &nbsp; ${b.bookname } &nbsp; 
		${b.publisher } &nbsp; ${b.price } &nbsp; <br>
	</c:forEach>
</body>
</html>