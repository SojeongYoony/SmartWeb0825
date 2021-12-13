<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style>
	.no {
		color: red;
	}
	.ok {
		color: blue;
	}
</style>
<script>
	
	$(document).ready(function(){
		fnPwCheck();
		fnPwCheck2();
		fnUpdatePw();
	}); // load event

	/* *********************************************** fnPwCheck() *********************************************** */
	// 비밀번호 변경 변수와 함수
	let pwPass = false;
	function fnPwCheck(){
		$('#pw').keyup(function(){
			let regPw = /^[0-9]{1,10}$/;
			if ( regPw.test($(this).val()) == false ) {
				$('#pw_result').text('비밀번호는 어쩌구저쩌구입니다.').addClass('no').removeClass('ok');
				pwPass = false;
			} else {
				$('#pw_result').text('사용 가능한 비밀번호 입니다.').addClass('ok').removeClass('no');
				pwPass = true;
			}
		}) // key up function
	} // end fnPwCheck
	
	let pwPass2 = false;
	function fnPwCheck2(){
		
		$('#pw2').keyup(function(){
			if ( $('#pw').val() != $(this).val() ) {
				$('#pw2_result').text('비밀번호를 확인하세요').addClass('no').removeClass('ok');
				pwPass2 = false;
			} else {
				$('#pw2_result').text('');
				pwPass2 = true;
			}
		}) // pw2 keyup function
		
	} // End fnPwCheck2
	
	// 비밀번호 변경 함수
	function fnUpdatePw(){
		$('#f').submit(function(event){
			if ( pwPass == false || pwPass2 == false ) {
				alert('비밀번호 입력을 확인하세요');
				event.preventDefault();
				return false;
			}
			return true;
			
		}); // submit 
	} // fnUpdatePw
	
</script>
</head>
<body>

	<h1>비밀번호 변경 화면</h1>
	
	<form id="f" method="post" action="/ex13/member/updatePw">
	
		<input type="hidden" name="email" value="${email}"> <!-- 수정 조건 -->
	
		비밀번호<br>
		<input type="password" name="pw" id="pw">
		<span id="pw_result"></span><br><br>
		
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2">
		<span id="pw2_result"></span><br><br>
		
		<button>비밀번호 변경하기</button>
	
	</form>

</body>
</html>