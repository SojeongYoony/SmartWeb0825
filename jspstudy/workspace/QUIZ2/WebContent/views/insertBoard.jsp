<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>추가하기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script>
	$(document).ready(function(){
		$("#fn").on('submit', function(event){
			if($('#writer').val().length == 0) {
				event.preventDefault();
				alert('작성자 입력 필수');
				$('#writer').focus();
				return false;
			}
			if($('#title').val().length == 0) {
				event.preventDefault();
				alert('제목 입력 필수');
				$('#title').focus();
				return false;
			}
			if($('#content').val().length == 0) {
				event.preventDefault();
				alert('내용 입력 필수');
				$('#content').focus();
				return false;
			}
			return true;
		})
		
	})
</script>

</head>
<body>
<form action="/QUIZ2/insert.board" method="post" id="fn">
	<table>
		<tbody>
			<tr>
				<td>작성자</td>
				<td><input name="writer" id="writer" type="text" placeholder="작성자"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input name="title" id="title" type="text" placeholder="글제목"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" id="content" rows="10" cols="40"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button>등록</button><input type="button" value="목록" onclick="location.href='/QUIZ2/selectList.board'"></td>
			</tr>
		</tbody>
	</table>
</form>
</body>
</html>