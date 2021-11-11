<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>게시글 보기(상세, 수정, 삭제)</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$('#fo').on('submit', function(event){
				if( $('#title').val() == '') {
					alert('제목을 입력해주세요');
					event.preventDefault();
					$('#title').focus();
					return false;
				} else if ( $('#content').val() == '' ) {
					alert('내용을 입력해주세요');
					event.preventDefault();
					$('#content').focus();
					return false;
				} else if( $('#title').val() == '${board.title}' && $('#content').val() == '${board.content}' ){
					alert('변경 사항 없음');
					event.preventDefault();
					$('#title').focus();
					return false;
				}
				return true;
			});
			
			$('#deleteBtn').on('click', function(){
				if(confirm('삭제할까요?') == false) {
					event.preventDefault();
					return false;
				} else {
					alert('삭제 성공');
					location.href='QBATI/delete.board?idx='+${board.idx}
				}
				return true;
			})
		});
	</script>



</head>
<body>
	<form action="/QBATI/update.board" id="fo">
		<table>
			<tbody>
				<tr>
					<td>순번</td>
					<td><input type="text" name="idx" value="${board.idx}" readonly></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${board.writer}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title" value="${board.title}"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" id="content" rows="20" cols="50">${board.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<button>수정</button>
						<input type="button" value="목록" onclick="location.href='/QBATI/selectAllList.board'">
						<input type="button" value="삭제" id="deleteBtn">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>