<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Page /수정/삭제</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
	}) //onload
</script>

</head>
<body>
	
	<h1>갤러리 상세 보기</h1>
	
	<form id="f" method="post" enctype="multipart/form-data"> <!-- file은 able only post -->
		
		작성자 : ${gallery.writer}<br>
		작성일 : ${gallery.created}<br>
		수정일 : ${gallery.lastModified}<br>
		작성IP : ${gallery.ip}<br>
		제목 : <input type="text" name="title" id="title" value="${gallery.title}"><br>
		내용<br>
		<input type="text" name="content" id="content" value="${gallery.content}"><br>
		첨부 변경하기<br>
		<input type="file" name="new_file"><br><br>
		기존 첨부 : ${gallery.origin}<br>
		<img alt="${gallery.origin}" src="/ex12/${gallery.path}/${gallery.saved}" width="500px">
	</form>
	<input type="button" value="목록" onclick="location.href='/ex12/gallery/selectGalleryList'">
	
</body>
</html>