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
	.out {
  		float: left;
  		margin: 50px;
  		padding: 20px;
  		width: 20%;
  		border: 1px solid;
	}	
	.out2 {
  		float: left;
  		margin: 50px;
  		padding: 20px;
  		width: 45%;
  		border: 1px solid;
	}	
	
	.goods_div{
		float: left;
	}
	
	#goodsDetail{
		margin: 30px;
		margin-left: 100px;
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
			})
		}
		
		function listGoods(){
			$.ajax({
				url:"listGoods",
				success:function(arr){
					$("#listGoods").empty();
					$.each(arr,function(){
						//var li = $("<li></li>").html(this.name).addClass("goods").attr("no",this.no);
						var li = $("<li></li>").html(this.name).addClass("goods").attr({no:this.no,name:this.name,qty:this.qty,price:this.price});
						$("#listGoods").append(li);
					});
				}
			});
		}
		
		listDept();
		listGoods();
		
		$(document).on("mouseover",".item",function(){
			$(this).addClass("high_light");
		});
		$(document).on("mouseleave",".item",function(){
			$(this).removeClass("high_light");
		});
		$(document).on("mouseover",".goods",function(){
			$(this).addClass("high_light");
		});
		$(document).on("mouseleave",".goods",function(){
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
		
		$(document).on("click",".goods",function(){
			var name = $(this).attr("name");
			var qty = $(this).attr("qty");
			var price = $(this).attr("price");
			$("#name").val(name);
		  	$("#price").val(qty);
			$("#qty").val(price);
			
			var no = $(this).attr("no");
			var data = {no:no};
			$(".goods").removeClass("active");
			$(this).addClass("active");
			$.ajax({
				url:"detailGoods",
				data:data,
				success:function(g){
					$("#goodsDetail").empty();
					var no = $("<p></p>").html("* 상품번호 : "+g.no);
					var name = $("<p></p>").html("* 상품이름 : "+g.name);
					var price = $("<p></p>").html("* 상품가격 : "+g.price);
					var qty = $("<p></p>").html("* 상품수량 : "+g.qty);
					var img = $("<img>").attr({
						src:"images/"+g.fname,
						width:200,
						heigh:200
					});
					
					$("#goodsDetail").append(no,name,price,qty,img);
				}
			});
		});
		
		$("#btnGoodsInsert").click(function(){
			var form = new FormData(document.getElementById("goodsForm"));
			$.ajax({
				url:"insertGoods",
				type:"post",
				processData:false,
				contentType:false,
				data:form,
				dataType:"json",
				success:function(){
					listGoods();
				}
			}); 
		});
	});
</script>
</head>
<body>
<div class="out">
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
</div>

<div class="out2">
	<h2>상품목록</h2>	
	<hr>
	<div class="goods_div">
		<form id="goodsForm" method="post" enctype="multipart/form-data">
			<br>
			상품이름 : <input type="text" name="name" id="name"><br>
			상품가격 : <input type="text" name="price" id="price"><br>
			상품수량 : <input type="text" name="qty" id="qty"><br>
			상품사진 : <input type="file" name="uploadFile"><br><br>
			<input type="button" id="btnGoodsInsert" value="등록">
			<input type="button" id="btnGoodsUpdate" value="수정">
		</form>
	</div>
	<div class="goods_div">
		<ul id="listGoods"></ul>
	</div>
	<div class="goods_div" id="goodsDetail"></div>
	
</div>

</body>
</html>