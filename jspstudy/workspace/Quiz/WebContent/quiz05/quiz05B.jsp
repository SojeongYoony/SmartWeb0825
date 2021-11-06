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

	</style>
</head>
<body>

			<% request.setCharacterEncoding("UTF-8"); %>


	<div id="box">
	------------------------------------------------------------------<br>
		1. 회원 정보는 웹 사이트 운영을 위해서만 사용됩니다.<br>
		2. 원치 않으면 정보 제공을 하지 않을 수 있습니다.<br>
		3. 다만 이 경우 정상적인 웹 사이트 이용이 제한됩니다.<br>
	------------------------------------------------------------------<br>
	</div>
		<p>위 약관에 동의하십니까?</p>
	<form action="">
		<input type="radio" name="agreement" value="true" id="agree_radio"><label for="agree_radio">동의 함</label>
		<input type="radio" name="agreement" value="false" id="disagree_radio"><label for="disagree_radio">동의 안 함</label>
		<input type="hidden" name="userId" value="<%=request.getParameter("userId")%>">
		<input type="hidden" name="userId" value="<%=request.getParameter("userPw")%>">
		<input type="hidden" name="userId" value="<%=request.getParameter("userName")%>">
			<button>회원가입</button>			
	</form>

</body>
</html>