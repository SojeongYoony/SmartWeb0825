<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

	
	request.setCharacterEncoding("UTF-8");
	String strAge = new String(request.getParameter("age"));
	int age = Integer.parseInt(strAge);
	int i = age >= 20 ? 1 : 0;
	String[] title = {"미성년자", "성인"};
	String[] able = {"불가능", "가능"};
	
		
%>

<h1><%=title[i]%></h1>

<div>당신의 나이는 <%=age%>이므로 주류 구매가 <%=able[i]%>합니다.</div>

<a href="02_quiz1.jsp">처음으로이동</a>