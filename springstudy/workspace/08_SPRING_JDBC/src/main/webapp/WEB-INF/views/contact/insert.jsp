<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style>

	input {
		display: block;
		margin:5px;
	}
</style>	
	
</head>
<body>
	<h1>연락처 등록 화면</h1>
	
	<form action="/ex08/contact/addContact" method="post">
		<input type="text" name="name" placeholder="이름">
		<input type="text" name="tel" placeholder="전화번호">
		<input type="text" name="address" placeholder="주소">
		<input type="text" name="birthday" placeholder="YYYY-MM-DD">
	
		<button>등록 완료</button>
	</form>
	<input type="button" value="목록" onclick="location.href='/ex08/contact/findAllContact'">
</body>
</html>