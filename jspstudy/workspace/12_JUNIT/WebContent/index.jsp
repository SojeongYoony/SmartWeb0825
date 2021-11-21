<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>시작화면</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<style>
		*{
			padding: 0;
			margin: 0;
			box-sizing: border-box;
		}
		.wrap {
			width: 1500px;
			margin: 0 auto;
			padding: 10px;
			display: flex;
		}
		.input_area {
			width: 300px;
			padding: 10px;
		}
		.list_area {
			width: 1200px;
			padding: 10px;
		}
		.list_area table {
			border-collapse: collapse;
			width: 100%;
		}
		.list_area table td {
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
			padding: 3px 5px;
			text-align: center;
		}
	</style>
	
	<script>
		$(document).ready(function(){
			fnSelectBoardList(); // 문서가 열리면 이거 해주세요
			fnInsertBoard();
			fnDeleteBoard();
			
		}); // page load
		
	<%------------------------------ fnSelectBoardList() ---------------------------- --%>		
		function fnSelectBoardList(){
			$.ajax({
				url: 'selectBoardList.do',
				type: 'get',
			// data : 서버로 보내는 파라미터  		-- 여기선 없음
			dataType: 'json', 	// DB에게서 받아오는 data
			success: function(boards){						// Board(DTO) type arr boards == board table의 모든 것들을 arr에 담았고 그것을 받을 때 boards로 받은 것. (board table의 여러 요소들이 들어 있으므로 복수형인 boards를 사용함.)
				// 기존 목록 지우기
				$('#board_list').empty();
				$.each(boards, function(i, board){			// $.each(배열, function(idx, 요소){});		여기서 요소 :: Board(DTO) type board
					$('<tr>')
					.append($('<td>').text(board.bNo))
					.append($('<td>').text(board.writer))
					.append($('<td>').text(board.content))
					.append($('<td>').text(board.bDate))
					.append($('<td>').html('<input type="hidden" name="bNo" value="'+board.bNo+'"><input type="button" value="삭제" class="delete_btn">'))				// *** tag를 만들어 넣어야 하므로 html 주의 ***
				//  .append($('<input type="hidden" name="bNo">').val(board.bNo))	// 부모로 지정하기 싫으면 만드는 형제로 html에 집어넣어서 하는 방법도 있음 / ***중요*** ajax로 할 때 번호를 어떻게 인식하게 할 건지 !(data속성을 이용하든 부모를 이용하든 형제를 이용하든 선택할 줄 알아야한다.)
					.appendTo('#board_list')
				})
			},
			error: function(){
				alert('실패');
			}
			}) // end ajax
		} // end fnSelectBoardList :: selectBoardList.do 하고 결과받아옴
	<%------------------------------ fnInsertBoard() ---------------------------- --%>		
		function fnInsertBoard(){
			$('#insert_btn').on('click', function(){
				if( $('#bNo').val().length != 5 ) {
					alert('게시글번호는 5자리입니다.');
					return;
			} // end if
				$.ajax({
					url: 'insertBoard.do',
					type: 'post',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(obj){
						alert(obj.result);
						fnSelectBoardList();		// 넣었을때 목록을 다시 보여줄 수 있도록 하자.
					},	// end success
	 				error: function(xhr){	// 응답 텍스트는 xhr 객체에 responseText 프로퍼티로 전달됨.
	 					if(xhr.status == 1111){	// response.setStatus(1111); 코드로 보낸 값을 받음.
		 					alert(xhr.responseText);	
	 					}
	 				}	// end error
				});	// ajax
		}); // end click evnet
	}// end fnInsertBoard
			
	
	<%------------------------------ fnDeleteBoard() ---------------------------- --%>		

		// $(this).prev().val() // delete_btn중 this == click 한  button // prev :: prev input tag // value == previous input tag's value // value == board.bNo // board.bNo == DB에서 받아온 data를 ajax data로 변환하여 만든 data 중 bNo
		function fnDeleteBoard(){
			$('body').on('click', '.delete_btn', function(){
				if (confirm('삭제할까요?')) {
				$.ajax({
					url: 'deleteBoard.do',
					type: 'get',
					data: 'bNo=' + $(this).prev().val(),
					dataType: 'json',
					success: function(obj){
						if (obj.result){
							alert('삭제성공');
							fnSelectBoardList();
						} else {
							alert('삭제 실패');
						}
					},
					error: function(){
						alert('코드 수정 필요');
					}
				})
					
				}// end if
			})
	};// end fnDelete Board																		***** checkbox / radio button 만들기 연습 하는 것도 좋음.
	
	</script>

</head>
<body>
	<div class="wrap">
		<div class="input_area">
			<form id="f">
				<input type="text" name="bNo" id="bNo" placeholder="5자리번호"><br>
				<input type="text" name="writer" id="writer" placeholder="작성자"><br>
				<input type="text" name="content" id="content" placeholder="내용"><br>
				<input type="button" value="등록하기" id="insert_btn">
			</form>
		</div>
		<div class="list_area">
			<table>
				<thead>
					<tr>
						<td>게시글번호</td>
						<td>작성자</td>
						<td>내용</td>
						<td>작성일자</td>
						<td></td>
					</tr>
				</thead>
				<tbody id="board_list"></tbody>
			</table>
		</div>
	</div>
</body>
</html>