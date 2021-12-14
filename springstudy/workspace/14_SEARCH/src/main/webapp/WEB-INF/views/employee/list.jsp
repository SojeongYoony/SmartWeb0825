<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<style>
		table { border-collapse: collapse; text-align: center; }
		td { padding: 0 2px 0 2px; }
		a { color: black; text-decoration: none; }
	</style>
	
<script>

	$(document).ready(function(){
		fnAreaSetting();
		fnInit();
		fnFindAll();
		fnFind();
	}); // onload event
	
	
/* ************************************************************* fnAreaSetting() ************************************************************* */	
	// 검색 화면 세팅 함수
	function fnAreaSetting(){
		$('#non_salary_area').css('display', 'none');
		$('#salary_area').css('display', 'none');
		$('#column').change(function(){
			if ($(this).val() == '') { // 선택 안 했을 경우 == 둘 다 none
				$('#non_salary_area').css('display', 'none');
				$('#salary_area').css('display', 'none');
			} else if ($(this).val() == 'SALARY') {
				$('#salary_area').css('display', 'inline');
				$('#non_salary_area').css('display', 'none');
			} else {
				$('#non_salary_area').css('display', 'inline');
				$('#salary_area').css('display', 'none');
			}
		}) // column Change fn
	} // End fnAreaSetting()
	
	
/* ************************************************************* fnInit() ************************************************************* */	
 	// 화면 초기화 함수
 	function fnInit(){
		$('#init_btn').click(function(){
			$('#column').val('');
			$('#query').val('');
			$('#min').val('');
			$('#max').val('');
			$('#non_salary_area').css('display', 'none');
			$('#salary_area').css('display', 'none');
		});
	} // End fnInit()
	
	
/* ************************************************************* fnFindAll() ************************************************************* */	
	// 전체 검색 함수
	function fnFindAll(){
		$('#find_all_btn').click(function(){
			location.href='/ex14/search/findAll';
		}) // click event
	} // End fnFindAll()

/* ************************************************************* fnFindAll() ************************************************************* */	
	// 검색 함수
	function fnFind(){
		$('#search_btn').click(function(){
			if ($('#column').val() == '') { // 아무 선택 없이 (column)검색한 경우
				alert('검색 카테고리를 선택하세요');
				$('#column').focus();
				return;
			}
			else if ($('#column').val() == 'EMPLOYEE_ID' && $('#query').val() == '') {
				alert('검색할 사원번호를 입력하세요.');
				$('#query').focus();
				return;
			}
			else if ($('#column').val() == 'FIRST_NAME' && $('#query').val() == '') {
				alert('검색할 사원의 이름을 입력하세요.');
				$('#query').focus();
				return;
			}
			else if ($('#column').val() == 'HIRE_DATE' && $('#query').val() == '') {
				alert('검색할 사원의 고용일을 입력하세요.');
				$('#query').focus();
				return;
			}
			$('#f').attr('action', '/ex14/search/findEmployee');
			$('#f').submit();
		}); // search_btn click event
	} // End fnFind ()
</script>
	

</head>
<body>

	
	<h1>사원 검색 화면</h1>
	
	<form id="f" method="get"> <!-- 검색은 GET! -->
		<select name="column" id="column">
			<option value="">:::선택:::</option>
			<option value="EMPLOYEE_ID">EMPLOYEE_ID</option>
			<option value="FIRST_NAME">FIRST_NAME</option>
			<option value="HIRE_DATE">HIRE_DATE</option>
			<option value="SALARY">SALARY</option>
		</select>
		<span id="non_salary_area">
			<input type="text" name="query" id="query">
		</span>
		<span id="salary_area">
			<input type="number" name="min" id="min" placeholder="최소연봉">
			~
			<input type="number" name="max" id="max" placeholder="최대연봉">
		</span>
		<br><br>
		<input type="button" value="검색" id="search_btn">
		<input type="button" value="초기화" id="init_btn">
		<input type="button" value="전체사원조회" id="find_all_btn">
	</form>
	
	<hr>

	
	<h1>사원 목록</h1>
	
	<table border="1">
		<thead>
			<tr>
				<td>NO</td>
				<td>EMP_ID</td> <!-- employee_Id -->
				<td>FIRST_NAME</td>
				<td>LAST_NAME</td>
				<td>HIRE_DATE</td>
				<td>SALARY</td>
				<td>DEPT_ID</td> <!-- department_id -->
				<td>DEPT_NAME</td>
				<td>LOCATION_ID</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="9">검색 결과 없음</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="employee" items="${list}" varStatus="vs">
					<tr>
						<td>${startNum - vs.index}</td> <!-- 107 - 1 -->
						<td>${employee.employeeId}</td>
						<td>${employee.firstName}</td>
						<td>${employee.lastName}</td>
						<td>${employee.hireDate}</td>
						<td>${employee.salary}</td>
						<td>${employee.department.departmentId}</td> <!-- 객체 안의 객체 안에 있는 property -->
						<td>${employee.department.departmentName}</td>
						<td>${employee.department.locationId}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<c:if test="${not empty paging}">
			<tfoot>
				<tr>
					<td colspan="9">${paging}</td>
				</tr>
			</tfoot>				
		</c:if>
	</table>
	
</body>
</html>