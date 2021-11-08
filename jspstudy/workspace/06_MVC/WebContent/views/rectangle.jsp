<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		너비 ${param.width}, 높이 ${param.height}인 사각형의 넓이는 ${area}입니다.	
		<!-- param.width, param.height는 request의 param에 저장되어있으므로 param으로 꺼내고 area는 attribute로 저장되어 있으므로 바로 가져올 수 있음.  -->
	</div>
	<a href="/MVC2/input.do">입력 화면으로 가기</a>
	

</body>
</html>