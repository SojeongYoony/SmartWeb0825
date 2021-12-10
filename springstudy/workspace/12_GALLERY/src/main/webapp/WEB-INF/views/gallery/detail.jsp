<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Page /수정/삭제</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.css" integrity="sha512-Cb0WDP6lVyVaQJulFMEOBGpkgqU6UAOEBpthbb9BfdhmUXnmYQwobuCm6Xp2YYL6Pd/l0ZA5Up/ijp0fu+nFpQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script>
	$(document).ready(function(){
		/* --------------!! 수정 !!----------------- */
		$('#update_btn').on('click', function(){
			if ($('#title').val() == '${gallery.title}' && 
				$('#content').val() == '${gallery.content}' &&
				$('#newFile').val() == '') {
				alert('수정사항 없음');
				return;
			}
			$('#f').attr('action', '/ex12/gallery/updateGallery');
			$('#f').submit();
		}) // update click event

		/* --------------!! 삭제 !!----------------- */
		$('#delete_btn').on('click', function(){
			if(confirm('삭제할까요?')){
				$('#f').attr('action', '/ex12/gallery/deleteGallery' );
				$('#f').submit();
			}
		}) // delete click event
		
	}) // onload
</script>

</head>
<body>
	
	<h1>갤러리 상세 보기</h1>
	<form id="f" method="post" enctype="multipart/form-data"> <!-- file은 able only post -->
		<input type="button" value="목록" onclick="location.href='/ex12/gallery/selectGalleryList'">
		<input type="button" value="수정완료" id="update_btn">
		<input type="button" value="삭제완료" id="delete_btn">
		<%-- 파일을 수정/삭제 하기 위해서 필요한 정보들 --%>
		<input type="hidden" name="no" id="no" value="${gallery.no}"> <!-- 누구를 수정/삭제 할 것이냐에 대한 정보 -->
		<input type="hidden" name="path" value="${gallery.path}"> <!-- 경로 -->
		<input type="hidden" name="saved" value="${gallery.saved}"> <!-- 저장 된 파일 이름 -->
		<input type="hidden" name="origin" value="${gallery.origin}">
		작성자 : ${gallery.writer}<br>
		작성일 : ${gallery.created}<br>
		수정일 : ${gallery.lastModified}<br>
		작성IP : ${gallery.ip}<br>
		제목 : <input type="text" name="title" id="title" value="${gallery.title}"><br>
		내용<br>
		<input type="text" name="content" id="content" value="${gallery.content}"><br>
		첨부 변경하기<br>
		<input type="file" name="newFile" id="newFile"><br><br>
	</form>
	
	<c:if test="${not empty gallery.origin}">
		기존 첨부 : ${gallery.origin}<br>
		<img alt="${gallery.origin}" src="/ex12/${gallery.path}/${gallery.saved}" width="500px"><br>
		<form action="/ex12/gallery/download" method="post"> <!-- downloaded origin file name / path / saved name ect.. * form for download -->
			<input type="hidden" name="origin" value="${gallery.origin}">
			<input type="hidden" name="path" value="${gallery.path}">
			<input type="hidden" name="saved" value="${gallery.saved}">
			<button>다운로드</button>
		</form>
	</c:if>
	
</body>
</html>