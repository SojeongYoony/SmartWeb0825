<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function fnModify(f) {
		
		 if ( $('#title').val() == '' ) {
			alert('제목은 필수!');
			return;
		} else if ( $('#title').val().trim() == '' ) {
			alert('제목을 입력해주세요!');
			return;
		} else if ( $('#content').val().trim() == '' ) {
			if ( confirm('입력된 내용이 없습니다. 수정하시겠습니까?') == false ) {
				return;
			}
		}
		f.action = '${pageContext.request.contextPath}/board/modify';
		f.submit();
	}
	function fnRemove(f) {
		if (confirm('정말 삭제하시겠습니까?')) {
			f.action = '${pageContext.request.contextPath}/board/remove';
			f.submit();
		}
	}
</script>
</head>
<body>
	
	<form>
		<table border="1">
			<tr>
				<td>순번</td>
				<td><input type="text" name="idx" value="${board.idx}" readonly />
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.writer}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"  id="title" value="${board.title}" />
			</td>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="content" id="content">${board.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="button" value="수정" onclick="fnModify(this.form)"/>
					<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/board/list'"/>
					<input type="button" value="삭제" onclick="fnRemove(this.form)"/>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>