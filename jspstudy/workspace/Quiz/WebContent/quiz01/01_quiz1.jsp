<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>

	
		<span>사이트명 : </span>
		<select name="site" onchange="location.href=this.value">
			<option value="https://www.google.com/" selected>구글</option>
			<option value="https://www.naver.com/">네이버</option>
			<option value="https://www.daum.net">다음</option>
			<option value="https://www.nate.com">네이트</option>
		</select>


	
</body>
</html>