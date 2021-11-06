<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 액셀의 숫자 형식 표현법과 같다. --%>
	
	<c:set var="n" value="12345.6789" />
	
	<div><fmt:formatNumber value="${n}" pattern="#,##0" /></div>	<%-- 반올림 --%>
	<div><fmt:formatNumber value="${n}" pattern="#,##0.0" /></div>	<%-- 반올림 --%>
	<div><fmt:formatNumber value="${n}" pattern="#,##0.00" /></div>	<%-- 반올림 --%>

	<div><fmt:formatNumber value="${n}" pattern="0" /></div>	<%-- 반올림 --%>
	<div><fmt:formatNumber value="${n}" pattern="0.0" /></div>	<%-- 반올림 --%>
	<div><fmt:formatNumber value="${n}" pattern="0.00" /></div>	<%-- 반올림 --%>
	<%-- 콤마 유무, 소수점 자리표현 --%>
	
	<div><fmt:formatNumber value="${n}" type="percent" /> </div>	<%-- 100을 곱해서 나타남 : 백분율 --%>
	<div><fmt:formatNumber value="${n}" type="currency" /> </div>
	<div><fmt:formatNumber value="${n}" type="currency" currencySymbol="$" /> </div>

</body>
</html>