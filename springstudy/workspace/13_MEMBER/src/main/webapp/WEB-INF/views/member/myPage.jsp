<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my Page</title>
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
		fnPwcheck0();
		fnPwCheck();
		fnPwCheck2();
		fnUpdatePw();
		fnUpdateMember();
		fnLeaveMember();
	}); // load event

	/* *********************************************** fnPwCheck() *********************************************** */
	// 비밀번호 변경 변수와 함수
	let pwPass0 = false;
	function fnPwcheck0(){
		$('#pw0').keyup(function(){
			if ($('#pw0').val() != '${loginUser.pw}') {
				$('#pw0_result').text('현재 비밀번호를 확인해 주세요').addClass('no').removeClass('ok');
				pwPass0 = false;
			} else if ( $('#pw0').val() == '${loginUser.pw}' ) {
				$('#pw0_result').text('현재 비밀번호와 일치').addClass('ok').removeClass('no');
				pwPass0 = true;
			}
		}) // key up function on pw0
	} // End fnPwcheck0()
	
	
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
	
	/* *********************************************** fnUpdatePw() *********************************************** */
		// 비밀번호 변경 함수
	function fnUpdatePw(){
		$('#f').submit(function(event){
			if ( pwPass0 == false || pwPass == false || pwPass2 == false ) {
				alert('비밀번호 입력을 확인하세요');
				event.preventDefault();
				return false;
			}
			$('#f').attr('action', '/ex13/member/updatePw');
			$('#f').submit();
			return true;
			
		}); // submit 
	} // fnUpdatePw
	
	/* *********************************************** fnEmailCheck() *********************************************** */
	// 이메일 중복체크 변수와 함수
	let emailPass = false;
	function fnEmailCheck(){
		if ( $('#email').val() == '${loginUser.email}' ) {
			emailPass = true;
			return;
		} // 같으면 점검할 필요 없음 
		
		// session에 올라간 user의 email정보와 현재 로그인 한 user의 email정보가 다르면 수정했다는 뜻이니, 정규식 검사 다시 진행
		let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+([.][a-zA-Z]{2,}){1,2}$/;
		if ( regEmail.test($('#email').val()) == false ) {
			alert('이메일 형식을 확인하세요.');
			emailPass = false;
			return;
		}
		// 중복 체크(DB사용)
		$.ajax({
			url : '/ex13/member/emailCheck',
			type: 'post',
			data: 'email=' + $('#email').val(),
			dataType: 'json',
			success: function(map){
				if( map.result == null ) {
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
	} // fnEmailCheck()
	
	
	/* *********************************************** fnUpdateMember() *********************************************** */
	// 회원정보 변경 함수
	function fnUpdateMember() {
		$('#updateMember_btn').click(function(){
			if ( $('#name').val() == '${loginUser.name}' && 
				 $('#email').val() == '${loginUser.email}' ) {
				alert('변경할 내용이 없습니다.');
				return;
			}
			fnEmailCheck();
			if ( emailPass == false ) {
				return;
			}
			$('#f').attr('action', '/ex13/member/updateMember');
			$('#f').submit();
			
		}); // End fnUpdateMember()
	}
	
	/* *********************************************** fnLeaveMember() *********************************************** */
	function fnLeaveMember() {
		$('#leave_btn').click(function(){
			if(confirm('탈퇴하시겠습니까?')) {
				$('#f').attr('action', '/ex13/member/leaveMember');
				$('#f').submit();
			}
			
		}) // End leaveClick Event
	}
	
</script>
</head>
<body>

	<h1>마이 페이지</h1>
	
	<form id="f" method="post">
		
		<input type="hidden" name="no" id="no" value="${loginUser.no}">
		
		회원번호<br>
		${loginUser.no}<br><br>
		
		아이디<br>
		${loginUser.id}<br><br>
		
		가입일<br>
		${loginUser.registed}<br><br>
		
		현재 비밀번호<br> <!-- 현재 비밀번호 틀리면 안되게끔 수정이 필요함 비밀번호 수정 시, -->
		<input type="password" name="pw0" id="pw0"><br><br>
		<span id="pw0_result"></span><br><br>
		
		새 비밀번호<br>
		<input type="password" name="pw" id="pw">
		<span id="pw_result"></span><br><br>
		
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2">
		<input type="button" value="비밀번호변경하기" id="updatePw_btn">
		<span id="pw2_result"></span><br><br>
			
		이름<br>
		<input type="text" name="name" id="name" value="${loginUser.name}"><br><br>
		
		이메일<br>
		<input type="text" name="email" id="email" value="${loginUser.email}"><br><br>
		
		<input type="button" value="회원정보변경하기" id="updateMember_btn">
		<input type="button" value="되돌아가기" onclick="location.href='/ex13'">
		<input type="button" value="회원탈퇴" id="leave_btn">
	</form>

</body>
</html>