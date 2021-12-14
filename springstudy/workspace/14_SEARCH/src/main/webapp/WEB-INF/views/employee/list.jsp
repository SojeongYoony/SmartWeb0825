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

</head>
<body>
	
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
					<td colspan="9">표시할 사원 없음</td>
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
		<tfoot>
			<tr>
				<td colspan="9">${paging}</td>
			</tr>
		</tfoot>				
	</table>
	
</body>
</html>