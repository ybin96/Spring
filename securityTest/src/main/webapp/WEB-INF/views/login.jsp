<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인</h2>
	<hr>
	<form action="/login" method="post">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="text" name="username" placeholder="아이디를 입력하세요"><br>
		<input type="password" name="password" placeholder="암호를 입력하세요"><br>
		<button type="submit">로그인</button>
	</form>
</body>
</html>