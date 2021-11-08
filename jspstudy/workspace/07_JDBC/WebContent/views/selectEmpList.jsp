<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>

	<div>
		<h1>사원 전체 목록</h1>
		<table border="1">
			<thead>
				<tr>
					<td>사원번호</td>
					<td>사원명</td>
					<td>삭제</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${empList}">
					<tr>
						<td>${emp.num}</td>
						<td>${emp.name}</td>
						<td>X</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>