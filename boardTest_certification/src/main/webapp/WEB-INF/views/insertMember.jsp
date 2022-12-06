<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#f,#div_code,#div_phoneCodeSend,#div_emailCodeSend{
		display: none;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var server_code;
		$("#phone_ok").click(function(){
			$("#div_phoneCodeSend").css("display","block");
			$("#div_emailCodeSend").css("display","none");
			$("#btnSendPhone").click(function(){
				var data = {phone:$("#phone").val()};
				$.ajax({
					url:"sendMsgCode",
					data:data,
					success:function(code){
						server_code = code;
						$("#div_code").css("display","block");
					}	
				});
			});
		});
		
		$("#email_ok").click(function(){
			$("#div_emailCodeSend").css("display","block");
			$("#div_phoneCodeSend").css("display","none");
			$("#btnSendMeg").click(function(){
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
		});
		
		$("#btnCheck").click(function(){
			var user_code = $("#user_code").val();
			if(user_code == server_code){
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
	인증방식 선택하기<br><br>
	<button id="email_ok">이메일 인증</button> &nbsp;&nbsp; <button id="phone_ok">전화번호 인증</button>
	<div id="div_phoneCodeSend">
		전화번호 : <input type="tel" id="phone"><button id="btnSendPhone">인증번호 받기</button>
	</div>
	<div id="div_emailCodeSend">
		이메일 : <input type="email" id="email"><button id="btnSendMeg">인증번호 받기</button>
	</div>
	<div id="div_code">
		인증번호 : <input type="text" id="user_code"><button id="btnCheck">인증확인</button>
	</div>
	
	<form action="insertMember" method="post" id="f">
		ID : <input type="text" name="id"><br>
		PWD : <input type="password" name="pwd"><br>
		NAME : <input type="text" name="name"><br>
		EMAIL : <input type="email" name="email"><br>
		PHONE : <input type="tel" name="phone"><br>
		<input type="submit" value="회원가입">
		<input type="reset" value="취소">
	</form>
</body>
</html>