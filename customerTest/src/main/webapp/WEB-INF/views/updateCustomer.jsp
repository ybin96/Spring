<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>고객수정</h2>
	<form action="updateCustomer" method="post">
		고객번호 : <input type="hidden" name="custid" value=${c.custid }><br>
		이름 : <input type="text" name="name" value=${c.name }><br>
		주소 : <input type="text" name="address" value=${c.address }><br>
		전화 : <input type="text" name="phone" value=${c.phone }><br>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
	</form>
</body>
</html>