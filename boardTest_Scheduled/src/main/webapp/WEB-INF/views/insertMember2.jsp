<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#f,#div_code{
		display: none;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var server_code;
		$("#btnSend").click(function(){
			var data = {email:$("#email").val()};
			$.ajax({
				url:"sendCode",
				data:data,
				success:function(data){
					server_code = data;
					$("#div_code").css("display","block");
				}
			});
		});
		
		$("#btnCheck").click(function(){
			var user_code = $("#user_code").val();
			if(server_code == user_code){
				alert("인증완료");
				$("#f").css("display","block");
			}else{
				alert("인증실패");	
			}
		});
	});
</script>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	
	이메일 : <input type="email" id="email"><button id="btnSend">이메일 인증</button>
	
	<div id="div_code">
		인증코드 : <input type="number" id="user_code"><button id="btnCheck">인증확인</button>
	</div>
	
	<form action="insertMember" method="post" id="f">
		ID : <input type="text" name="id"><br>
		PWD : <input type="password" name="pwd"><br>
		NAME : <input type="text" name="name"><br>
		EMAIL : <input type="email" name="email"><br>
		<input type="submit" value="회원가입">
		<input type="reset" value="취소">
	</form>
</body>
</html>