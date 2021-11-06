<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<c:set var="age" value="17" />
		
		<c:choose>
			<c:when test="${age - 0 < 0 or age - 100 > 0}" >나이가 아니다</c:when>
			<c:when test="${7-age >= 0}">미취학아동</c:when>
			<c:when test="${13 - age >= 0}">초등학생</c:when>
			<c:when test="${16 - age >= 0}">중학생</c:when>
			<c:when test="${19 - age >= 0}">고등학생</c:when>
			<c:otherwise>성인</c:otherwise>
		</c:choose>

</body>
</html>