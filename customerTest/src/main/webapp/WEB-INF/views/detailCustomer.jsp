<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(custid){
		if(confirm("정말로 삭제하시겠어요?")){
			location.href="deleteCustomer?custid="+custid;
		}
	}
</script>
</head>
<body>
	<h2>고객 상세</h2>
	<hr>
	${c.custid }<br>
	${c.name }<br>
	${c.address }<br>
	${c.phone }<br>
	<hr>
	<a href="updateCustomer?custid=${c.custid }">수정</a>
	<a href="#" onclick="check(${c.custid})">삭제</a>
</body>
</html>