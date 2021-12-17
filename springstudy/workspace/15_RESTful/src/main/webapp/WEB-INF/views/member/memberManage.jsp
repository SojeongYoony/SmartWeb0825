<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberManage</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style>
	table {
		border-collapse: collapse;
		text-align: center;
	}
	td {
		width: 100px;
		padding: 0 2px 0 2px;
	}
	#paging {
		display: flex;
		justify-content: center;
	}
	#paging > div {
		width: 20px;
		heigth: 20px;
		text-align: center;
	}
	.disable_link {
		color: lightgray;
	}
	.enable_link {
		cursor: pointer;
	}
	.now_page {
		color: red;
	}
</style>
<script>
	$(document).ready(function(){
		fnInit();
		fnFindAllMember();
		fnAddMember();
		fnFindMember();
		fnModifyMember();
		fnRemoveMember();
		fnChangePage();
	}); // End load Event
/* ****************************************************** fnInit() ********************************************************** */
	// 입력 폼 초기화 함수
	function fnInit() {
			$('#memberNo').val('');
			$('input[type=text]').val('');
			$('input:radio[name="gender"]').prop('checked', false);
			$('#id').removeAttr('readonly');  // 조회 시, 적용 한 readonly 속성 제거 $('#id').prop('readonly', false);
	}
/* ****************************************************** fnFindAllMember() ********************************************************** */
 // 전체 회원 목록 함수 + page 전역 변수를 선언 for paging
 	var page = 1; // 시작은 무조건 1page이니까. 1로 초기화
	function fnFindAllMember(){
		$.ajax({
			url: '/ex15/api/members/page/' + page,
			type: 'get',
			dataType: 'json',
			success: function(map){
				fnPrintMemberList(map);
				fnPrintPaging(map.pageUtils);
			}
		}) // End ajax
	}
	
/* ****************************************************** fnPrintMemberList() ********************************************************** */
	// 회원 목록 출력만 하는 함수
	function fnPrintMemberList(map){
		/* 목록 초기화 */
		$('#member_list').empty(); // 중복되지 않도록 초기화 작업을 가장 먼저 해 줘야한다 잊지말자.
		
		/* 페이지 처리 모든 정보를 변수 p에 저장 */
		var p = map.pageUtils; 
		if (p.totalRecord == 0) {
			$('<tr>')
			.append( $('<td colspan="5">').text('등록된 회원이 없습니다.') )
			.appendTo( '#member_list' );
		} else {
			$.each(map.list, function(i, member){
				$('<tr>')
				.append( $('<td>').text(member.id) )
				.append( $('<td>').text(member.name) )
				.append( $('<td>').text(member.address) )
				.append( $('<td>').text(member.gender) )
				.append( $('<td>').html( $('<input type="hidden" name="memberNo" value="'+member.memberNo+'"><input type="button" value="조회" class="view_btn">')))
				.appendTo( '#member_list' );
			})
		} // End if
	} // End fnPrintMemberList()

/* ****************************************************** fnPrintPaging() ********************************************************** */
	// 페이징 출력 함수
	function fnPrintPaging(p) {
		// 페이징 영역 초기화
		$('#paging').empty();
		// 1페이지로 이동
		if (page == 1) {
			$('<div class="disable_link">&lt;&lt;</div>').appendTo('#paging');
/* 			$('<div>').addClass('disable_link').html('&lt;&lt;').appendTo('#paging'); */
		} else {
			$('<div class="enable_link" data-page="1">&lt;&lt;</div>').appendTo('#paging'); 
/* 			$('<div>').addClass('enable_link').html('&lt;&lt;').attr('data-page', 1).appendTo('#paging');  */
		}
		// 이전 블록으로 이동
		if (page <= p.pagePerBlock) {
			$('<div class="disable_link">&lt;</div>').appendTo('#paging');
		} else {
			$('<div class="enable_link" data-page="'+(p.beginPage-1)+'">&lt;</div>').appendTo('#paging');
		}
		// 페이지 번호
		for (let i = p.beginPage; i <= p.endPage; i++) {
			if (i == page) {
				$('<div class="disable_link now_page">'+ i +'</div>').appendTo('#paging');
			} else {
				$('<div class="enable_link" data-page="'+ i +'">'+ i +'</div>').appendTo('#paging');
			}
		}
		// 다음블록으로 이동
		if (p.endPage == p.totalPage) {
			$('<div class="disable_link">&gt;</div>').appendTo('#paging');
		} else {
			$('<div class="enable_link" data-page="'+(p.endPage+1)+'">&gt;</div>').appendTo('#paging');
		}
		
		// 마지막 페이지로 이동
		if (page == p.totalPage) {
			$('<div class="disable_link">&gt;&gt;</div>').appendTo('#paging');
		} else {
			$('<div class="enable_link" data-page="'+p.totalPage+'">&gt;&gt;</div>').appendTo('#paging');
		}
	} // End fnPrintPaging

/* ****************************************************** fnChangePage() ********************************************************** */
	// 페이징 링크 처리 함수 (전역변수 page의 값을 바꾸고, fnFindAllMember() 함수 호출 ) -- 현재 클릭한 대상의 data속성에서
	function fnChangePage() {
		$('body').on('click', '.enable_link', function(){
			page = $(this).data('page');
			fnFindAllMember();
		}) // body click event
	} // End fnChangePage
	

/* ****************************************************** fnAddMember() ********************************************************** */
	// 회원 등록 함수
	function fnAddMember() {
		$('#insert_btn').click(function(){
			let member = JSON.stringify({
				id: $('#id').val(),
				name: $('#name').val(),
				gender: $('input:radio[name="gender"]:checked').val(),
				address: $('#address').val()
			});
			$.ajax({
				url: '/ex15/api/members',
				type: 'post',
				contentType: 'application/json',
				data: member,
				dataType: 'json',
				success: function(map){
					if(map.result > 0) {
						alert('회원번호 ' + map.memberNo + '인 회원이 등록되었습니다.');
						fnFindAllMember();
						fnInit();
					} else {
						alert('회원이 등록되지 않았습니다.');
					}
				},
				error: function(xhr){
					if (xhr.status == 500) {
						alert(xhr.responseText);
					} else if (xhr.status == 600) {
						alert(xhr.responseText);
					}
				}
			})
		}); // insert_btn click Event
	} // End fnAddMember
	
/* ****************************************************** fnFindMember() ********************************************************** */
	// 회원 조회 함수
	function fnFindMember() {
		$('body').on('click', '.view_btn', function(){
			$.ajax({
				url: '/ex15/api/members/' + $(this).prev().val(), // url에 param이 아니라, 변수가 포함되어 넘어간다.
			 // data: data는 없다 경로에 포함시켜서 가므로 data를 따로 보낼게 없다.
				type: 'get',
				dataType: 'json', // 받아오는 data Type
				success: function(map){
					if (map.member != null) { // 번호를 만들지 않았으므로 수정/삭제가 불가능함 -> mapper에 숫자가 없음
						$('#memberNo').val(map.member.memberNo); // 이제 있음..
						$('#id').val(map.member.id).attr('readonly', true); // attr : js의 문법이 느슨하여 prop와 동일하게 사용해도 ...
						$('#name').val(map.member.name);
						$('#address').val(map.member.address);
						$('input:radio[name="gender"][value="'+map.member.gender+'"]').prop('checked', true); // prop는 객체에 저장 된 값이므로 true or false가 된다
					} else {
						alert($(this).prev().val() + '번 회원 정보가 없습니다.');
					}
				}
			})
		}) // view btn click event
	}
/* ****************************************************** fnModifyMember() ********************************************************** */
	// 회원 정보 수정 함수
	function fnModifyMember() {
		$('#update_btn').click(function() {
			let member = JSON.stringify({
				memberNo: $('#memberNo').val(),
				// id: readonly로 수정을 못 하도록 막아뒀으므로 굳이 실어서 보낼 필요는 없음 
				name: $('#name').val(),
				gender: $('input:radio[name="gender"]:checked').val(),
				address: $('#address').val()
			});
			$.ajax({
				url: '/ex15/api/members',
				type: 'put', // insert와 동일하지만 달라지는 부분
				contentType: 'application/json', // 보내는 dataType 알려줘야함
				data: member, // member 변수에 저장된 values 보냄
				dataType: 'json',
				success: function(map){
					if(map.result > 0) {
						alert('회원 정보가 수정되었습니다.');
						fnFindAllMember();
					} else {
						alert('회원 정보가 수정되지 않았습니다.');
					}
				}
			}) // ajax
		}) // update_btn click event
	} // End fnModifyMember
	
/* ****************************************************** fnRemoveMember() ********************************************************** */
	// 회원 삭제 함수
	function fnRemoveMember() {
		$('#delete_btn').click(function(){
			if(confirm('삭제할까요?')) {
				$.ajax({
					url: '/ex15/api/members/' + $('#memberNo').val(),
					type: 'delete',
					dataType: 'json',
					success: function(map){
						if(map.result > 0) {
							alert('회원이 삭제되었습니다.');
							fnFindAllMember();
							fnInit();
						} else {
							alert('회원이 삭제되지 않았습니다.');
						}
					}
				})
			}
		})
	}

</script>

</head>
<body>

	<h1>회원 관리</h1>
	
	<div>
		<input type="hidden" name="memberNo" id="memberNo">
		아이디 <input type="text" name="id" id="id"><br>
		이름   <input type="text" name="name" id="name"><br>
		주소   <input type="text" name="address" id="address"><br>
		성별
		<input type="radio" name="gender" id="male" value="남"><label for="male">남</label>
		<input type="radio" name="gender" id="female" value="여"><label for="female">여</label><br>
		<input type="button" value="초기화" id="init_btn" onclick="fnInit()">
		<input type="button" value="등록" id="insert_btn">
		<input type="button" value="수정" id="update_btn">
		<input type="button" value="삭제" id="delete_btn">
	</div>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>주소</td>
				<td>성별</td>
				<td>조회</td>
			</tr>
		</thead>
		<tbody id="member_list"></tbody>
		<tfoot>
			<tr>
				<td colspan="5"><div id="paging"></div></td>
			</tr>
		</tfoot>
	</table>	
</body>
</html>