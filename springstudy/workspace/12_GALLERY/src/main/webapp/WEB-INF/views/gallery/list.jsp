<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트 화면</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.css" integrity="sha512-Cb0WDP6lVyVaQJulFMEOBGpkgqU6UAOEBpthbb9BfdhmUXnmYQwobuCm6Xp2YYL6Pd/l0ZA5Up/ijp0fu+nFpQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
	table {
		border-collapse: collapse;
	}
	
	a {
		color: orange;
		text-decoration: none;
	}
</style>
</head>
<body>

	<h1>갤러리 목록 보기</h1>
	<a href="/ex12/gallery/insertPage">새 글 작성</a>
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>썸네일</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>첨부다운로드</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="6">작성된 갤러리 없음</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="gallery" items="${list}">
					<tr>
						<td>${gallery.no}</td>
						<%-- 	<c:if test="${empty gallery.origin}">
								<td colspan="2"></td>
							</c:if> --%>
						<td><img alt="${gallery.origin}" src="/ex12/${gallery.path}/s_${gallery.saved}"></td>
						<td><a href="/ex12/gallery/selectGalleryByNo?no=${gallery.no}">${gallery.title}</a></td>
						<td>${gallery.writer}</td>
						<td>${gallery.created}</td>
						<td>
							<c:if test="${not empty gallery.origin || gallery.saved }">
								<form action="/ex12/gallery/download" method="post"> <!-- downloaded origin file name / path / saved name ect.. * form for download -->
									<input type="hidden" name="origin" value="${gallery.origin}">
									<input type="hidden" name="path" value="${gallery.path}">
									<input type="hidden" name="saved" value="${gallery.saved}">
									<button><i class="fas fa-paperclip"></i></button>
								</form>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>