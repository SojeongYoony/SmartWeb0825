<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% request.setCharacterEncoding("UTF-8"); %>
	
	${param.kor}
	${param.eng}
	${param.mat}
	
	<h1>평균 : ${(param.kor + param.eng + param.mat) / 3.0 }</h1>
	<c:set var="average" value="${(param.kor + param.eng + param.mat) / 3.0 }" />
	<c:set var="grade" value="${(param.kor + param.eng + param.mat) / 3.0 }"></c:set>
		<c:choose>
		<c:when test="${average - 0 < 0 or  - 100 > 0}" >잘못된점수이다.</c:when>
		<c:when test="${average - 90 >= 0}">평균 : ${average }점, 학점 : A</c:when>
		<c:when test="${average - 80 >= 0}">평균 : ${average }점, 학점 : B</c:when>
		<c:when test="${average - 70 >= 0}">평균 : ${average }점, 학점 : C</c:when>
		<c:when test="${average - 60 >= 0}">평균 : ${average }점, 학점 : D</c:when>

		<c:otherwise>F</c:otherwise>
	</c:choose>
</body>
</html>