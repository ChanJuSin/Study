<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공부용 게시판 Error</title>
</head>
<body>

<h1>서버 내부적인 오류가 발생했습니다. 관리자에게 문의해주십시오.</h1>
<h2>${error.getMessage() }</h2>
<ul>
	<c:forEach items="${error.getStackTrace() }" var="stack">
		<li>${stack.toString() }</li>
	</c:forEach>
</ul>
</body>
</html>