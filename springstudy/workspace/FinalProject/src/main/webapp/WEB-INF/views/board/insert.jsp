<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function fnAdd(f) {
		if ( $('#writer').val() == '' ) {
			alert('작성자 필수!');
			return;
		} else if ( $('#writer').val().trim() == '' ) {
			alert('작성자명을 입력해주세요!');
			return;
		} else if ( $('#title').val() == '' ) {
			alert('제목은 필수!');
			return;
		} else if ( $('#title').val().trim() == '' ) {
			alert('제목을 입력해주세요!');
			return;
		} else if ( $('#content').val().trim() == '' ) {
			if ( confirm('입력된 내용이 없습니다. 등록하시겠습니까?') == false ) {
				return;
			}
		}
		
		f.action = '${pageContext.request.contextPath}/board/add';
		f.submit();
	}
</script>
</head>
<body>

	<form method="post">
		<table border="1">
			<tbody>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" id="writer" autofocus /></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="10" cols="50" name="content" id="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="등록" onclick="fnAdd(this.form)" />
						<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/board/list'"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

</body>
</html>