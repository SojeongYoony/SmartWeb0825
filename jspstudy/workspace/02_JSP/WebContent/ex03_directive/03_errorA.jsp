<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%-- 현재 페이지에서 에러가 나면 03_error.jsp로 이동하라. --%>    
<%@ page errorPage="03_error.jsp" %>

<%
	// Integer.parseInt("날바꿔봐");		 		// --> java.lang.NumberFormatException 강제 발생
	String name = request.getParameter("name");	// --> java.lang.NullPointerException 강제 발생
	if (name.equals("홍길동")) {
		
	}
%>