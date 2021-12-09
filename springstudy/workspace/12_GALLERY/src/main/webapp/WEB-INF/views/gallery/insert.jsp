<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX - 시작화면</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		fnFileCheck();
	});
	
	function fnFileCheck(){
		
		$('#file').on('change', function(){
			/* 확장자 점검 */
			let origin = $(this).val(); // 첨부된 파일명
			let extName = origin.substring(origin.lastIndexOf(".") + 1).toUpperCase(); // 확장자를 대문자로 저장 aaa.aaa.aaa.ccc 일 때, 마지막 마침표 다음 자리부터 끝까지 substring으로 가지고오고 
			if ( $.inArray(extName, ['JPG', 'JPEG', 'GIF', 'PNG']) == -1 ) {	// 첨부된 파일이 ['JPG', 'JPEG', 'GIF', 'PNG'] 중 하나가 아니면 (-1) :: 확장자 제한 두기
				alert('확장자가 jpg, jpeg, gif, png인 파일만 업로드 가능합니다.');
				$(this).val(''); // 첨부 초기화
				return;
			}			
			
			/* 파일크기 점검 */
			let maxSize = 1024 * 1024 * 10;	// 최대 크기 10MB
			let fileSize = $(this)[0].files[0].size; // 첨부된 파일 크기
			if (fileSize > maxSize) {
				alert('10MB 이하의 파일만 업로드가 가능합니다.');
				$(this).val('');
				return;
			}
			
		}) // change function
		
	} //fn FileCheck
</script>
</head>
<body>

	<h1>갤러리 작성 화면</h1>
	
	<form id="f" action="/ex12/gallery/insertGallery" method="post" enctype="multipart/form-data">
	
		작성자<br>
		<input type="text" name="writer"><br><br>
		
		제목<br>
		<input type="text" name="title"><br><br>
		
		이미지<br>
		<input type="file" name="file" id="file"><br><br>
		
		내용<br>
		<input type="text" name="content"><br><br>
		<button>작성완료</button>
	</form>
	<input type="button" value="목록" onclick="location.href='/ex12/gallery/selectGalleryList'">
</body>
</html>