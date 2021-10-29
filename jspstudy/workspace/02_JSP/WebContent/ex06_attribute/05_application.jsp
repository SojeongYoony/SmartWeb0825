<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<% 
	
	//BufferedReader br = new BufferedReader(new FileReader("D:\\SmartWeb0809\\jspstudy\\workspace\\02_JSP\\WebContent\\ex06_attribute\\next_level.txt"));
	//StringBuilder sb = new StringBuilder();
	
	// 읽어 들일 파일의 실제 경로			--- 1. 준비  ---
	String realPath = application.getRealPath("ex06_attribute/next_level.txt");
	
	// 문자 기반 입력 스트림(reader) 생성
	BufferedReader br = new BufferedReader(new FileReader(realPath));
	
	// 읽어 들이기
	// 한 줄 읽고 <br> 붙여서 result에 누적
	String result = "";
	while(true) {
		String line = br.readLine();
		if (line==null) {
			break;
		}
		result += line + "<br>";
	}
	
	// 스트림 닫기
	if (br != null) {
		br.close();	
	}

%>


	<h1>NEXT_LEVEL</h1>
	<div>
		<%=result%>
	</div>
