<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.high_light{
		background: pink;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$(".row").click(function(){
			var no = $(this).attr("no");
			location.href="detailBoard?no="+no;
		});
		
		$(".row").hover(function(){
			$(this).addClass("high_light");
		},function(){
			$(this).removeClass("high_light");
		});
	});
</script>
</head>
<body>
	<h2>게시판 목록</h2>
	<hr>
	<a href="insertBoard">게시물 추가</a>
	
	<table border=1 width="60%">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="b" items="${list }">
			<tr class="row" no="${b.no }">
				<td>${b.no }</td>
				<td>
					<c:if test="${b.b_level > 0 }">
						<c:forEach begin="1" end="${b.b_level }">
							&nbsp;&nbsp;
						</c:forEach>
						<img src="re.png" width="20" height="20">
					</c:if>
					${b.writer }
				</td>
				<td>${b.title }</td>
				<td>${b.regdate }</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	
	<c:if test="${startPage > 1 }">
		<a href="listBoard?pageNUM=${startPage-1 }">[이전]</a>
	</c:if>
	&nbsp;
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="listBoard?pageNUM=${i }">${i }</a>&nbsp;
	</c:forEach>
	&nbsp;
	<c:if test="${endPage < totalPage }">
		<a href="listBoard?pageNUM=${endPage+1 }">[다음]</a>
	</c:if>
</body>
</html>