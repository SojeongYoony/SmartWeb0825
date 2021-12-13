<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script>
	$(document).ready(function(){
		fnLogin();
	}); // load event
	
	function fnLogin(){
		$('#f').submit(function(){
			let regId = /^[a-z]{1,10}$/;  // 실제 서비스는 수정해서 사용
			let regPw = /^[0-9]{1,10}$/;
			if ( regId.test($('#id').val()) == false || regPw.test($('#pw').val()) == false ) {
				alert('아이디와 비밀번호를 확인하세요');
				event.preventDefault();
				return false;
			}
			return true;	
		}) // end submit event
	}
	
</script>

</head>
<body>

	<h1>로그인 화면</h1>
	
	<form id="f" method="post" action="/ex13/member/login">
		아이디<br>
		<input type="text" name="id" id="id"><br><br>
		비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>
		<button>로그인</button>
	</form>
	
	
	<div>
		<a href="/ex13/member/findIdPage">아이디찾기</a> / 
		<a href="/ex13/member/findPwPage">비밀번호찾기</a>
	</div>
</body>
</html>