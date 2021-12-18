<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board Manage</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">

	// 페이지 로드
	$(document).ready(function(){
		fnInit();
		fnFileCheck();
		fnAddBoard();
	});
/* ------------------------------------------------------------ fnInit() ------------------------------------------------------------ */
	function fnInit(){
		$('#writer').val('');	
		$('#title').val('');	
		$('#content').val('');	
		$('#files').val('');	
	}
/* ------------------------------------------------------------ fnFileCheck() ------------------------------------------------------------ */
	// 첨부파일 점검 함수 (확장자 + 크기)
	function fnFileCheck() {
			$('#files').change(function(){
				// 첨부파일 확장자 / 파일크기 점검
				let regFile = /(.*)\.(jpg|jpeg|png|gif)$/;
				let maxSize = 1024 * 1024 * 10;	// 최대 크기 10MB
				// 모든 첨부
				let files = $(this)[0].files;
				// 각 첨부 순회
				for (let i = 0; i < files.length; i++) {
					// 확장자 체크
					if(regFile.test(files[i].name) == false) {
						alert('이미지만 첨부할 수 있습니다.');
						$(this).val('');
						return;
						
					}
					// 크기 체크
					if (files[i].size > maxSize) { // files[i].size : 첨부된 파일 크기
						alert('10MB 이하의 파일만 업로드가 가능합니다.');
						$(this).val('');
						return;
					}
				}
				
			}) // origin change event
	}
/* ------------------------------------------------------------ fnAddBoard() ------------------------------------------------------------ */
	// 게시판 등록 함수
	function fnAddBoard(){
		$('#insert_btn').click(function(){
			if ($('#files').val() == '') {
				alert('첨부는 필수입니다.');
				return;
			}
			// ajax의 파일업로드는 form 객체 사용 // 첨부를 FormData에 넣기    -- 첨부가 하나인 경우 files[0]
			var formData = new FormData();
			formData.append('writer', $('#writer').val());
			formData.append('title', $('#title').val());
			formData.append('content', $('#content').val());
			let files = $('#files')[0].files;
			for (let i = 0; i < files.length; i++) {
				formData.append('files', files[i]);  // 첨부를 FormData에 넣기				
			}
			
			$.ajax({
					url:'/ex15/api/boards',
					type:'post', // file 첨부는 어차피 post만 가능
					contentType: false, // ajax 파일 첨부할 때 세팅   -- 첨부일 경우, 약속
					processData: false, // ajax 파일 첨부할 때 세팅   -- 첨부일 경우, 약속
					data: formData,
					dataType: 'json',
					success: function(map){
							if (map.boardResult && map.boardResult > 0 ) {
								if (map.boardAttachResult > 0) {
									fnShowAttachedFile(map);
								} else {
									alert('첨부 실패');
								}
								fnInit();
							} else {
								alert(map.errorMsg);
							}
					}
			}) // End ajax
		}) // insert_btn click event
	} // End fnAddBoard
/* ------------------------------------------------------------ fnShowAttachedFile() ------------------------------------------------------------ */
	// 첨부된 파일 확인 함수
	function fnShowAttachedFile(map) {
		$('#upload_result').empty();
		$.each(map.thumbnails, function(i, thumbnail){
			$('#upload_result')
			.append( $('<div>').html( $('<img>').attr('src', '/ex15/' + map.path + '/' + thumbnail) ) );
		})
	}
</script>

</head>
<body>

	<h1>게시판 관리</h1>
	<div>
		<form id="f">
			작성자 <input type="text" id="writer"><br>
			제목   <input type="text" id="title"><br>
			내용   <input type="text" id="content"><br>
			첨부   <input type="file" id="files" multiple><br> <!-- multiple : 다중첨부 가능 -->
			<input type="button" value="등록" id="insert_btn">
		</form>
	</div>

	<hr>

	<div id="upload_result">
		<input id="name">
	</div>
	
	
</body>
</html>