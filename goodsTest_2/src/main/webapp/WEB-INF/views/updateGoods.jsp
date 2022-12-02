<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품수정</h2>
	<hr>
	<form action="updateGoods" method="post" enctype="multipart/form-data">
		상품번호 : ${g.no }<br>
		<input type="hidden" name="no" value="${g.no }">
		상품이름 : <input type="text" name="name" value="${g.name }"><br>
		상품가격 : <input type="number" name="price" value="${g.price }"><br>
		상품수량 : <input type="number" name="qty" value="${g.qty }"><br>
		<input type="hidden" name="fname" value="${g.fname }">
		<img src="images/${g.fname }" width="50" height="50"><br>
		파일이름 : <input type="file" name="uploadFile"><br>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
	</form>
</body>
</html>