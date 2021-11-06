<%@page import="java.util.ArrayList"%>
<%@page import="dto.Board"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
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

	<%-- 1, 2, 3, 4, 5 --%>
	<c:forEach var="i" begin="1" end="5" step="1">
		${i}&nbsp;
	</c:forEach>
	
	<hr>
	
	<%-- 5, 4, 3, 2, 1 --%>
	<c:forEach var="j" begin="1" end="5" step="1">
		${ ( 1 + 5 ) - j}&nbsp;
	</c:forEach>
	
	<hr>
	
	<%-- 1월 ~ 12월 --%>
	<select name="month">
		<c:forEach var="m" begin="1" end="12" step="1">
			<option value="${m}">${m}월</option>
		</c:forEach>
	</select>
	
	<hr>
	
	<%-- 배열 --%>
	<% 
		String[] menu = {"김밥", "떡볶이", "순대"}; 
		pageContext.setAttribute("menu", menu);
	%>  <%-- 일반 자바 영역의 변수는 EL로 사용 못 함 --%>	<%-- 저장해야함 --%>
	
	<c:forEach var="food" items="${menu}" varStatus="v">	<%-- menu에 저장되어 있는 항목들이 food로 전달되어 나온다. varStatus:배열의 idx가 필요한 경우가 있으면 varStatus값을 잡고 idx를 추출 : v(정할 수 있다).idx(키워드다 : 변경x) --%>
		인덱스 : ${v.index}, 요소 번호 : ${v.count}, 요소 : ${food}<br>
		<%-- 배열과 ArrayList 사용하는 방법은 같다. --%>
	</c:forEach>
	
	<hr>
	
	<%-- List : 많이 사용하게 될 것 ! DB에서 가져온 Data가 여럿 존재하는 경우 List에 담아 forEach를 사용한다. --%>
	<%-- DB에서 가져온 정보는 DTO를 사용 한다. Data Transfer Object(bean) --%>
	<%
		List<String> hobbies = Arrays.asList("여행", "요가", "독서");
		pageContext.setAttribute("hobbies", hobbies);
	%>
	<c:forEach var="hobby" items="${hobbies}" varStatus="k">
		인덱스 : ${k.index}, 요소 : ${hobby} <br>
	</c:forEach>
	
	
	<%-- List<Board> --%>
	<%
		List<Board> list = new ArrayList<>();
		list.add(new Board("공지사항", "관리자", 1589));
		list.add(new Board("필독", "카페지기", 5489));
		list.add(new Board("출석!", "서교동멋쟁이", 0));
		pageContext.setAttribute("list", list);
	%>
	
	<table border="1">
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${list}" varStatus="v">
				<tr>
					<td>${v.count}</td>
					<td><a href="#?no=${v.count}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.view}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>