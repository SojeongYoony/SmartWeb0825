<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberDetail</title>
</head>
<body>

	<h1>회원번호 : ${member.idx} </h1>
	<h1>회원아이디 : ${member.id}</h1>
	<h1>회원이름 : ${member.name}</h1>
	
	<a href="/ex04">index로 돌아가기</a>	
	<!-- Mapping을 contextPath로 작성하여 GetMapping("/")를 동작시킬 수 있다. -->
	<!-- @GetMapping("/") 매핑과 연결됨. -->	
	<br>
	<a href="/ex04/index.do">index로 돌아가기</a>
	<!-- @GetMapping("index.do") 매핑과 연결됨. -->	
	<!-- folder 구조가 복잡해지면 contextPath를 작성하여 직접 어디로 가야할 지 작성해 주는 것이 좋다. -->
</body>
</html>