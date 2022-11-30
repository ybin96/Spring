<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>고객정보</h2>
	<hr>
	<a href="insertCustomer">고객등록</a>
	<br>
	<ul>
		<c:forEach var="c" items="${list }">
			<li><a href="detailCustomer?custid=${c.custid }">${c.name }</a></li>
		</c:forEach>
	</ul>
</body>
</html>