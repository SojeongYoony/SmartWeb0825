<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
	<style>
			label{
			display : inline-block;
			width : 100px;
			cursor : pointer;
		}
	</style>
</head>
<body>

	<form action="quiz05B.jsp" method="post">
		<div>
			<label>아이디</label>
			<input type="text" name="userId" >
		</div>
		<div>
			<label>비밀번호</label>
			<input type="password" name="userPw" >
		</div>
		<div>
			<label>이름</label>
			<input type="text" name="userName" >
		</div>
		<div>
			<button>로그인</button>
		</div>
	</form>
</body>
</html>