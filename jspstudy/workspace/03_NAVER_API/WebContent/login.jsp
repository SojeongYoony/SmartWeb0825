<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="/naver.api/ValidationServlet" method="post">		<!-- login package : ValidationServlet으로 가는 것  -->
		<div>
			<input type="text" name="id" placeholder="ID">
		</div>
		<div>
			<input type="text" name="pw" placeholder="Password">
		</div>
		<br><br>
		<img src="storage/${filename}" alt="캡차이미지">
		<input type="button" value="새로고침" onclick="location.href='/naver.api/LoginServlet'">	<!-- 페이지를 다시 받아옴 -> refresh 됨 -->
		<br>
		<input type="text" name="value">
		<input type="hidden" name="key" value="${key}">				<!-- request에 실려있으니 EL로 불러올 수 있고 -->
		<div>
			<button>로그인</button>	<!-- 로그인을 할 때 전달되는 세가지 id/pw/value(key값) -->
		</div>
	</form>
	
	<!-- 포워드는 리퀘스트에 담아두기만 하면 전달 되니까 포워드 하고 있음. -->
	
</body>
</html>