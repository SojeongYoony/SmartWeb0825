<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX PAGE</title>
<!-- JQUERY사용을 위해, CDN을 작성 함 -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style>
	input {
		display:block;
	}
</style>
<script>
	// pageload event
	$(document).ready(function(){
		$('#btn1').on('click', function(){ fnAjax1(); });
		$('#btn2').on('click', function(){ fnAjax2(); });
		$('#btn3').on('click', function(){ fnAjax3(); });
		$('#btn4').on('click', function(){ fnAjax4(); });
	}) // end onload
	// 함수
	function fnAjax1(){
			$.ajax({
				url: 'member/v1.do',							// Controller의 Mapping의 value와 SET
			   type: 'get',										// Controller의 @GetMapping or @PostMapping...etc와 SET
			   data: 'id=' + $('#id').val(), // id라는 parameter로 data를 보내는 것 이다. -- Controller의 매개변수 부분과 SET
		   dataType: 'text',									// Controller의 Mapping의 produces와 SET
			success: function(result) {
				$('#result').empty(); // 기존내역 지우기
				$('#result').text(result);
			},
			   error: function(xhr) {
				$('#result').empty(); // 기존내역 지우기
				$('#result').text('실패' + xhr.responseText);
			   }
			});//ajax
	} // End fnAjax1
	
	function fnAjax2() {
		$.ajax({
			url: 'member/v2.do',
		   type: 'get',
		   data: $('#f').serialize(), // form의 모든 요소를 parameter로 넘김
	   dataType: 'json',
	    success: function(member) { // member = { "id" : "aaa", "pw" : "sss" } // jackson에 의해서 변환되어 옴
	    	$('#result').empty();
			$('<ul>')
			.append( $('<li>').text(member.id) )
			.append( $('<li>').text(member.pw) )
			.appendTo('#result');
	       },
	       error: function(xhr){
	    	   $('#result').empty();
	    	   $('#result').text(xhr.responseText);
	       }
		})//ajax
	} // End fnAjax2
	
	function fnAjax3(){
		$.ajax({
			url: 'member/v3.do',
		   type: 'get',
		   data: $('#f').serialize(),
	   dataType: 'json',
		success: function(map){
			$('#result').empty();
			$('<h1>').text(map.id).appendTo('#result');
			$('<h1>').text(map.pw).appendTo('#result');
		  },
		  error: function(xhr){
	    	   $('#result').empty();
	    	   $('#result').text(xhr.responseText);
		  }
		}); // ajax
	}; // end fnAjax3
	
	function fnAjax4(){
		$.ajax({
			url: 'member/v4.do',
		   type: 'post', // json 데이터를 본문에 포함시켜서 전송  // 1
		   data: JSON.stringify({ //문자열 형식으로 변환    	  // 2
			       "id": $('#id').val(), 
			       "pw": $('#pw').val()
			     }), // json 데이터를 서버로 보냄 : json 형태로 바꿔서 보냄 -- 보내는 데이터가 json이다
	contentType: 'application/json', // 보내는 data의 type -- server에서 확인 // 3   -- 1, 2, 3은 SET!
	   dataType: 'json',     
	    success: function(map){
    	   $('#result').empty();
	    	$('<ol>')
	    	.append($('<li>').text(map.id))
	    	.append($('<li>').text(map.pw))
	    	.append($('<li>').text(map.addOn))
	    	.appendTo('#result');
	    },
	      error: function(xhr){ // 매개변수 확인 잘 하기.
	    	   $('#result').empty();
	    	   $('#result').text(xhr.responseText);
	      }
		 
		}); // ajax
	}; // fnAjax4
	
	
	
	
	
</script>

</head>
<body>

	<form id="f">
		<input type="text" name="id" id="id" placeholder="ID">
		<input type="password" name="pw" id="pw" placeholder="Password">
		<input type="button" value="전송1" id="btn1">
		<input type="button" value="전송2" id="btn2">
		<input type="button" value="전송3" id="btn3">
		<input type="button" value="전송4" id="btn4">
	</form>
	<div id="result"></div>
	
	<hr>
	
	<form id="f2">
		<input type="text" name="title" id="title" placeholder="제목">
		<input type="text" name="content" id="content" placeholder="내용">
		<input type="button" value="전송1" id="button1">
		<input type="button" value="전송2" id="button2">
		<input type="button" value="전송3" id="button3">
	</form>
	<div id="result2"></div>
	
	<hr>
	<a href="openPapago.do">파파고</a>
	
	<script>
		$('#button1').on('click', function(){
			$.ajax({
				url: 'board/v1.do',
				type: 'get',
				data: $('#f2').serialize(),
				dataType: 'json',
				success: function(board){
					$('#result2').empty();
					$('<h1>').text(board.title).appendTo('#result2');
					$('<h1>').text(board.content).appendTo('#result2');
				},
				error: function(xhr) {
					$('#result2').empty();
					$('#result2').text(xhr.responseText);
				}
			}) // ajax
		}) // button1
		
		$('#button2').on('click', function(){
			// json 만들어 보내고 json 받기
			$.ajax({
				url: 'board/v2.do',
				type: 'post',
				data: JSON.stringify({
					"title" : $('#title').val(),
					"content" : $('#content').val()
				}),
				contentType: 'application/json',
				dataType:'json',
				success: function(result){
					$('#result2').empty();
					$('<h1>').text(result.title).appendTo('#result2');
					$('<h1>').text(result.content).appendTo('#result2');
				},
				error: function(xhr) {
					$('#result2').empty();
					$('#result2').text(xhr.responseText);
				}
			})
			
		}) // button2
		
		$('#button3').on('click', function(){
	//		"title + content + hit" json 데이터로 보내고 받아오기
			$.ajax({
				url: 'board/v3.do',
				type: 'post',
				data: JSON.stringify({
					"title" : $('#title').val(),
					"content" : $('#content').val(),
					"hit" : 0
				}),
				contentType: 'application/json',
				dataType:'json',
				success: function(map) {
					$('#result2').empty();
					$('<h1>').text(map.title).appendTo('#result2');
					$('<h1>').text(map.content).appendTo('#result2');
					$('<h1>').text(map.hit).appendTo('#result2');
				},
				error: function(xhr) {
					$('#result2').empty();
					$('#result2').text(xhr.responseText);
				}
			})
			
		}) // button3
	</script>
</body>
</html>