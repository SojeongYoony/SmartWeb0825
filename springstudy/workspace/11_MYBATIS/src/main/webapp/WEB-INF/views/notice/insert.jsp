<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INSERT PAGE</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		
		$('#f').on('submit', function(event){
			
			if( $('#title').val() == '' ) {
				alert('제목을 입력해주세요');
				$('#title').focus();
				event.preventDefault();
				return false;
			} else if ( $('#content').val() == '' ) {
				alert('내용을 입력하세요');
				$('#content').focus();
				event.preventDefault();
				return false
			}
			return true;
			
		})//submit event
		
	})//onload
</script>

</head>
<body>
	
	<h1>공지사항 작성 화면</h1>
	<form action="/ex11/notice/insertNotice" method="post" id="f" >
		<input type="text" name="title" id="title" placeholder="제목"><br>
		<textarea rows="2" cols="21" name="content" id="content" placeholder="내용"></textarea><br>
		<button>작성완료</button>
		<input type="button" value="목록가기" onclick="location.href='/ex11/notice/selectNoticeList'">
	</form>
</body>
</html>