<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시작페이지</title>
</head>
<body>
	
	<h1>회원아이디 : ${member1.id}</h1>	<!-- getId가 동작 -->
	<h1>회원비밀번호 : ${member1.pw}</h1>
	<a href="memberView.do">회원정보보기</a>
	<br>
	<a href="boardView.do">게시글보기</a>
</body>
</html>