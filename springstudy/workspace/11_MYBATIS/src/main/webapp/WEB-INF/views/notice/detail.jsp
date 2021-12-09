<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail - update</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		
		$('#update_btn').on('click', function(){
			if( $('#title').val() == '${notice.title}' && $('#content').val() == '${notice.content}') {
				alert('수정할 내용이 없다.');
				return;
			}
			$('#f').attr('action', '/ex11/notice/updateNotice');
			$('#f').submit();
			alert('수정 완료!');
		}); // update btn click event
		
		$('#delete_btn').on('click', function(){
			if (confirm('삭제할까요?')) {
				$('#f').attr('action', '/ex11/notice/deleteNotice');
				$('#f').submit();
			}
		}); // delete btn click event
		
		
	}); // onload function
</script>

</head>
<body>
	
	<h1>공지사항 상세 보기 화면</h1>
	<form id="f">
		번호 : ${notice.no}<br><br>
		제목<br>
		<input type="text" name="title" id="title" value="${notice.title}"><br><br>
		내용<br>
		<textarea id="content" name="content" rows="2" cols="22" >${notice.content}</textarea><br><br>
		작성일<br>
		${notice.created}<br><br>
		최종수정일<br>
		${notice.lastModified}<br><br>
		<input type="hidden" name="no" value="${notice.no}">
		<input type="button" value="수정" id="update_btn">
		<input type="button" value="삭제" id="delete_btn">
		<input type="button" value="목록" onclick="location.href='/ex11/notice/selectNoticeList'">
	</form>
	
</body>
</html>