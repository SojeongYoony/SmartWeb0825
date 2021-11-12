<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>제품 등록 화면</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<title>Insert Form</title>
	<style>
		label {
			display : block;
		}
	</style>
	<script>
			$(document).ready(function(){
				
				// 제품명 중복 체크
				$('#name').on('blur', function(){
					$.ajax({						   // 요청 & 응답 :: 여기
						/* 보내는 곳 */
						url: '/AJAX/nameCheck.do',	   // action	// 데이터를 들고 서버로 가기 때문에 서버 경로 작성 : ajax url : server :: 서버로이동 == submit
						type: 'get',				   // method // 전달 방식 :: get or post
						data: 'name=' + $(this).val(), //params						// parameter 만듦  // 원래 작성하던 방식에서 ? 뒷 부분이라고 기억 // this :: event 대상
						
						/* 받아 오는 곳 :: json */
						dataType: 'json',				// 정답 == true or false :: 있다 or 없다
						success: function(resData){     // << 서버로 부터 응답 받은 data :: 외우면됨 약속 된 것
						// console.log(resData);		// controller에서 보낸 데이터 여기로 받음. :: resData안에 있음.
							if (resData.result == false) {
								alert('동일한 제품이 있습니다.');
							}
						},
						error: function(){
							alert('제품명 중복 체크 실패');
						}
					});
				})
				
				// 제품 등록
				$('#insert_btn').on('click', function(){
					$.ajax({
						url: '/AJAX/insert.do',
						type: 'post',
						// data: 'name=' + $('#name').val(), '&price=' + $('#price').val(), --- 방법 1. 가능은 하나 쌩 노가다이므로 요소가 여러개 일 경우 form 통째로 보내는 아래 방법을 사용하도록 하자.
						data: $('#f').serialize(),	// 폼의 모든 요소를 파라미터로 넘김 :: form의 정보(parameter)를 하나씩 넘김
						dataType: 'json',
						success: function(resData){	// obj가 여기로 온다
							if(resData.result > 0) {
								alert('제품 등록 성공.');
								$('#name').val('');
								$('#price').val('');
							} else {
								alert('제품 등록 실패.');
							}
						},		// ----- page이동 없이 ajax의 특성으로 제품등록만 한 페이지에서 계속 할 수 있음 (DB에 데이터 계속 들어감.)
						error: function(){
							alert('제품 등록 실패.');		// exception과 error를 함께 사용할 수 있다.
						}
					});
				})
				
				// 마지막 제품명 확인
				$('#name_btn').on('click', function(){
					$.ajax({
						url: '/AJAX/prevInsertName.do',
						type: 'get',
						dataType: 'json',
						success: function(resData){
							alert(resData.name);							
						},
						error: function(){
							alert('확인 실패');
						}
					})
				})
				
				
				
				
				
			})
	</script>
</head>
<body>

		<div>
			<h1>제품 등록 화면</h1>
			<form id="f">		<!-- action="/AJAX/insert.do" method="post" :: AJAX 에서 동작하므로form에는 작성하지 않아도 된다. -->
				<label for="name">제품명</label>
				<input type="text" name="name" id="name">
				<input type="button" id="name_btn" value="마지막 등록 제품명 확인">
				<label for="price">제품가격</label>
				<input type="text" name="price" id="price">
				<br>
				<input type="button" id="insert_btn" value="제품등록">
			</form>
		</div>

</body>
</html>