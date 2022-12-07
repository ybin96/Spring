<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 상세보기</h2>
	<hr>
	<a href="listBoard">목록보기</a>
	<a href="insertBoard">등록하기</a>
	<a href="updateBoard?no=${b.no }">수정하기</a>
	<a href="deleteBoard?no=${b.no }">삭제하기</a>
	<a href="insertBoard?no=${b.no }">답글</a>
	<br>
	<br>
	글번호 : ${b.no }<br>
	글제목 : ${b.title }<br>
	작성자 : ${b.writer }<br>
	글내용 <br>
	<textarea rows="10" cols="80" readonly="readonly">${b.content }</textarea>
	<br>
	조회수 : ${b.hit }<br>
	등록일 : ${b.regdate }<br>
	파일명 : ${b.fname }
	<hr>
</body>
</html>