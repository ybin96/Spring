<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>고객등록</h2>
	<form action="insertCustomer" method="post">
		고객번호 : <input type="number" name="custid"><br>
		이름 : <input type="text" name="name"><br>
		주소 : <input type="text" name="address"><br>
		전화 : <input type="text" name="phone"><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
	</form>
</body>
</html>