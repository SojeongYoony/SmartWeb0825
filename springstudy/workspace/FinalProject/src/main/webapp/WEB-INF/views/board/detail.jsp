<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function fnModify(f) {
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
				<td><input type="text" name="title" value="${board.title}" />
			</td>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="content">${board.content}</textarea></td>
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