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
		fnIdCheck();
		fnPwCheck();
		fnPwCheck2();
		fnEmailCheck();
		fnSendAuthcode();
		fnJoin();
	}); // load event

	/* *********************************************** fnIdCheck() *********************************************** */
	// 아이디 중복체크 변수와 함수
	let idPass = false;
	function fnIdCheck(){
		
		$('#id').keyup(function(){
			// 정규식 체크
			let regId = /^[a-z]{1,10}$/;  // 실제 서비스는 수정해서 사용
			if ( regId.test( $(this).val() ) == false ) {
				$('#id_result').text('아이디는 어쩌구저쩌구 입니다.').addClass('no');
				$('#id_result').removeClass('ok');
				idPass = false;
				return;
			} // if
			
			// 중복 체크(DB사용)
			$.ajax({
				url : '/ex13/member/idCheck',
				type: 'post',
				data: 'id=' + $(this).val(),
				dataType: 'json',
				success: function(map){
					if( map.result == null ) {
						$('#id_result').text('사용 가능한 아이디 입니다.').addClass('ok');
						idPass = true;
					} else {
						$('#id_result').text('사용중인 아이디 입니다.').addClass('no').removeClass('ok');
						idPass = false;
					}
				},
				error : function(xhr){
					$('#id_result').text(xhr.responseText).addClass('no');
					idPass = false;
				}
			}); // end ajax
			
		}); // id keyup event
		
	}; // End fnIdCheck
	
	
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
	
	/* *********************************************** fnEmailCheck() *********************************************** */
	// 이메일 중복체크 변수와 함수
	let emailPass = false;
	function fnEmailCheck(){
		$('#email').blur(function(){
			let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+([.][a-zA-Z]{2,}){1,2}$/;
			if ( regEmail.test($(this).val()) == false ) {
				alert('이메일 형식을 확인하세요.');
				emailPass = false;
				return;
			}
			// 중복 체크(DB사용)
			$.ajax({
				url : '/ex13/member/emailCheck',
				type: 'post',
				data: 'email=' + $(this).val(),
				dataType: 'json',
				success: function(map){
					if( map.result == null ) {
						alert('가입 가능한 이메일입니다. 인증번호받기를 클릭해서 이메일 인증을 진행해 주세요.');
						emailPass = true;
					} else {
						alert('사용중인 이메일 입니다. 다른 이메일을 사용해 주세요');
						emailPass = false;
					}
				},
				error : function(){
					emailPass = false;
				}
			}); // end ajax
		}); // End keyup function
	} // fnEmailCheck()
	
	
	/* *********************************************** fnGetAuthcode() *********************************************** */
	// 이메일 인증코드 전송함수
	function fnSendAuthcode(){
		$('#authCode_btn').click(function(){
			
			if(emailPass == false) {
				alert('이메일을 확인하세요');
				return;
			}
			
			$.ajax({
				url: '/ex13/member/sendAuthCode',
				type: 'post',
				data: 'email=' + $('#email').val(),
				dataType: 'json',
				success: function(map){ // service에서 생성하고 생성한 것을 받아온다.
					alert('인증코드가 발송되었습니다.')
					fnVerifyAuthcode(map.authCode); // 성공하면 인증코드가 무엇이다. 라는게 map에 있어야 한다.
				},
				error : function(){
					alert('인증코드 전송 실패');
				}
			}); // end ajax
			
		}); // fnGetAuthcode() click event
	} // End fnGetAuthcode
	
	/* *********************************************** fnVerifyAuthcode() *********************************************** */
	// 인증코드 검증 변수와 함수
	let authCodePass = false;
	function fnVerifyAuthcode(authCode){
		$('#verify_btn').click(function(){
			if ( $('#authCode').val() == authCode ) {
				alert('인증되었습니다.');
				authCodePass = true;
			} else {
				alert('인증에 실패했습니다.');
				authCodePass = false;
			}
			
		}); // end click
	}
	
	/* ************************************* fnJoin() ********************************** */
	// 회원가입 함수
	function fnJoin(){
		$('#f').submit(function(event){
			if ( idPass == false ) {
				alert('아이디를 확인하세요.');
				event.preventDefalut();
				return false;
			} else if (pwPass == false || pwPass2 == false) {
				alert('비밀번호를 확인하세요.');
				event.preventDefault();
				return false;
			} else if ( authCodePass == false) {
				alert('이메일 인증을 받아야 합니다.');
				event.preventDefault();
				return false;
			}
			return true;
		});
		
		
	} // fnJoin();
	
</script>
</head>
<body>

	<h1>회원가입 화면</h1>
	
	<form id="f" method="post" action="/ex13/member/join">
	
		아이디<br>
		<input type="text" name="id" id="id">
		<span id="id_result"></span><br><br>
		
		비밀번호<br>
		<input type="password" name="pw" id="pw">
		<span id="pw_result"></span><br><br>
		
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2">
		<span id="pw2_result"></span><br><br>
			
		이름<br>
		<input type="text" name="name" id="name"><br><br>
		
		이메일<br>
		<input type="text" name="email" id="email"><br><br>
		<input type="button" value="인증번호받기" id="authCode_btn"><br>
		<input type="text" name="authCode" id="authCode">
		<input type="button" value="인증하기" id="verify_btn"><br><br>
		
		<button>가입하기</button>
	
	</form>

</body>
</html>