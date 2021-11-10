<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$('#fn').on('submit', function(event){
				if($('#title').val() == '${board.title}' && $('#content').val()== '${board.content}'){
					alert('변경 사항이 없습니다.');
					event.preventDefault();
					return false;
				}
				return true;
			})
			
			$('#deleteBtn').on('click', function(){
				if(confirm('삭제할까요?') == false) {
					event.preventDefault();			// link의 기본 이벤트를 막음 == href를 통한 이동을 막음
					return false;
				} else {
					alert('삭제 성공');
					location.href='/QUIZ2/delete.board?idx='+${board.idx}
				}
				return true;	
			})
		})
	</script>

</head>
<body>
	<div>
		<form action="/QUIZ2/update.board" id="fn">
			<table border="1">
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
						<td><textarea name="content" id="content" rows="10" cols="40">${board.content}</textarea></td>
					</tr>
					<tr>
						<td colspan="2">
						<button>수정</button>
						<input type="button" value="목록" onclick="location.href='/QUIZ2/selectList.board'">
						<input type="button" id="deleteBtn" value="삭제">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>