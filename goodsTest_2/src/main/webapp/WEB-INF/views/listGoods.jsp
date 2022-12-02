 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.high_light{
		background: pink;
	}
	
	#op{
		display: none;
	}
	
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".item").hover(function(){
			$(this).addClass("high_light");
		},function(){
			$(this).removeClass("high_light");
		});
		
		
		$(".item").click(function(){
			var no = $(this).attr("no");
			location.href = "detailGoods?no="+no;
		});
		
		$("#searchType").change(function(){
			var c_name = $(this).val();
			if(c_name == "name"){
				$("#op").css("display","none");
			}else{
				$("#op").css("display","inline")
			}
		});
		
		$("#f").submit(function(){
			var searchType = $("#searchType").val();
			var op = $("#op").val();
			var keyword = $("#keyword").val();
			
			sessionStorage.setItem("searchType",searchType);
			sessionStorage.setItem("op",op);
			sessionStorage.setItem("keyword",keyword);
		});
		
		var sc = sessionStorage.getItem("searchType");
		var op = sessionStorage.getItem("op");
		var keyword = sessionStorage.getItem("keyword");
		
		$("#searchType > option[value="+sc+"]").attr("selected","selected")
		$("#op > option[value='"+op+"']").attr("selected","selected");
		if(keyword != null && keyword != "null"){
			$("#keyword").val(keyword);
		}
		
		console.log("sc:"+sc);
		if(sc == null || sc == 'null' || sc == "name"){
			console.log("검색칼럼이 name");
			$("#op").css("display","none");
		}else{
			console.log("검색칼럼이 name이 아님!");
			$("#op").css("display","inline")
		}
		
		$("#btnReset").click(function(){
			sessionStorage.removeItem("searchType");
			sessionStorage.removeItem("op");
			sessionStorage.removeItem("keyword");
			location.href="listGoods?reset=yes";
		});
	});
</script>
</head>
<body>
	
	<h2>상품 목록</h2>
	<hr>
	<a href="insertGoods">상품등록</a><br>
	
	<form action="listGoods" method="get" id="f">
		<select name="searchType" id="searchType">
			<option value="no">상품번호</option>
			<option value="name" selected="selected">상품이름</option>
			<option value="price">상품가격</option>
			<option value="qty">상품수량</option>
		</select> 
		<select id="op" name="op">
			<option value="=">=</option>
			<option value=">=">>=</option>
			<option value="<="><=</option>
			<option value=">">></option>
			<option value="<"><</option>
			<option value="!=">!=</option>
		</select>
		<input type="search" name="keyword" id="keyword">
		<input type="submit" value="검색">
	</form>
	<button id="btnReset">초기화</button>
	
	<table border="1" width="80%">
		<tr>
			<td><a href="listGoods?column=no">상품번호</a></td>
			<td><a href="listGoods?column=name">상품이름</a></td>
			<td><a href="listGoods?column=price">가격</a></td>
			<td><a href="listGoods?column=qty">수량</a></td>
			<td><a href="listGoods?column=fname">파일이름</a></td>
		</tr>
		
		<c:forEach var="g" items="${list }">
			<tr class="item" no="${g.no }">
				<td>${g.no }</td>
				<td>${g.name }</td>
				<td>${g.price }</td>
				<td>${g.qty }</td>
				<td>${g.fname }</td>
			</tr>
		</c:forEach>
	</table>
	
	<hr>
	<c:forEach var="i"  begin="1" end="${totalPage }" >
		<a href="listGoods?pageNUM=${i }">${i }</a> &nbsp;&nbsp;
	</c:forEach>
	
	
</body>
</html>












