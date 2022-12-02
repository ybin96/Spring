<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#update").click(function(){
			var no = $("#no").html();
			location.href="updateGoods?no="+no;
		});
		
		$("#delete").click(function(){
			if(confirm("정말로 삭제하시겠어요?")){
				var no = $("#no").html();
				location.href="deleteGoods?no="+no;
			}
		});
	});
</script>
</head>
<body>
	<h2>상품 상세</h2>
	<hr>
	상품번호 : <span id="no">${g.no }</span><br>
	상품이름 : ${g.name }<br>
	상품가격 : ${g.price }<br>
	상품수량 : ${g.qty }<br>
	<img src="images/${g.fname }" width="100" height="100">
	<hr>
	<a id="update" href="#">수정</a>
	<a id="delete" href="#">삭제</a>
</body>
</html>