<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	.high_light{
		background: pink;
	}
	.active{
		background: yellow;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		function listDept(){
			$.ajax({
				url:"listDept",
				success:function(arr){
					$("#list").empty();
					$.each(arr,function(){
						var tr = $("<tr></tr>").addClass("item");
						var td1 = $("<td></td>").html(this.dno);
						var td2 = $("<td></td>").html(this.dname);
						var td3 = $("<td></td>").html(this.dloc);
						$(tr).append(td1,td2,td3);
						$("#list").append(tr);
					});
				}
			});
		}
		
		listDept();
		
		$(document).on("mouseover",".item",function(){
			$(this).addClass("high_light");
		});
		$(document).on("mouseleave",".item",function(){
			$(this).removeClass("high_light");
		});
		
		$(document).on("click",".item",function(){
			var td_list = $(this).find("td");
			$("#dno").val($(td_list[0]).html());
			$("#dname").val($(td_list[1]).html());
			$("#dloc").val($(td_list[2]).html());
			
			$(".item").removeClass("active");
			$(this).addClass("active");
		});
		
		$("#btnInsert").click(function(){
			var data = $("#f").serializeArray();
			$.ajax({
				url:"insertDept",
				data:data,
				success:function(re){
					console.log(re)
					listDept();
				}
			});
		});
		
		$("#btnUpdate").click(function(){
			var data = $("#f").serializeArray();
			$.ajax({
				url:"updateDept",
				data:data,
				success:function(re){
					listDept();
				}
			})
		});
		
		$("#btnDelete").click(function(){
			if(confirm('정말로 삭제하시겠어요?')){
				var data = {dno:$("#dno").val()}
				$.ajax({
					url:"deleteDept",
					data:data,
					success:function(re){
						listDept();
					}
				});
			}
		});
		
	});

</script>
</head>
<body>
	<h2>부서목록</h2>
	<hr>
	<br>
	<table border="1">
		<thead>
			<tr>
				<td>부서번호</td>
				<td>부서명</td>
				<td>부서위치</td>
			</tr>
		</thead>
		<tbody id="list"></tbody>
	</table>
	<br>
	<hr>
	<br>
	<form id="f">
		부서번호 : <input type="text" name="dno" id="dno"><br>
		부서이름 : <input type="text" name="dname" id="dname"><br>
		부서위치 : <input type="text" name="dloc" id="dloc"><br><br>
		<input type="button" value="등록" id="btnInsert">
		<input type="button" value="수정" id="btnUpdate">
		<input type="button" value="삭제" id="btnDelete">
	</form>

</body>
</html>