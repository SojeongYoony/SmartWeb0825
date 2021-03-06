<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<style type="text/css">
		label{ display: block; }
	</style>
</head>
<body>

	<script>
		$(document).ready(function(){

		let success = ${applicationScope.success}
		
		if (success) {
			$('#writer').val('');
			$('#title').val('');
			$('#content').val('');
		//	$('input[type=text]').val('');	
		//  --> 나의 생각 인데 이 방법은 date도 날려버린다. 그리고 textarea는 input이 아니어서 벨류가 안날라감
		}
			
		});
	</script>

	<h1>문의 남기기</h1>
	<form action="04_applicationB.jsp">
		<label>
			작성일 <input type="text" name="date" value="<%=new Date(System.currentTimeMillis())%>">
		</label>
		<label>
			작성자 <input type="text" name="writer" id="writer">
		</label>
		<label>
			제&nbsp;&nbsp;목 <input type="text" name="title" id="title">
		</label>
		<textarea rows="5" cols="30" name="content" placeholder="문의 내용" id="content"></textarea>
		<br><br>
		<button>문의하기</button>
	</form>

</body>
</html>