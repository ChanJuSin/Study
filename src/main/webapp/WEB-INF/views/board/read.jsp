<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공부용 게시판 상세보기 페이지</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/read.css">
<%@ include file="../include/import.jsp" %>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<main class="read-page">
			<section class="read-page_header">
				<div class="read-page_info">
					<img src="/upload/displayFile?filePath=${pageInfo.boardImageVO.thumbnail_file_path }&distinction=profile" />
					<div class="read-page_user-info">
						<a href="#"><p>${pageInfo.boardVO.writer }</p></a>
						<span><fmt:formatDate value="${pageInfo.boardVO.crea_time }" pattern="yyyy-MM-dd hh:mm:dd"/></span>
					</div>
				</div>
				
				<div class="read-page_attachments">
					<input type="button" value="첨부파일(${fn:length(pageInfo.boardFileVO )})" />
				</div>
			</section>
			
			<section class="read-page_body">
				<div class="read-page_title">
					<p>#${pageInfo.boardVO.idx }</p>
					<h3>테스트용 게시글 입니다.</h3>
				</div>
				
				<div class="read-page_content">
					${pageInfo.boardVO.content }
				</div>
			</section>
		</main>
	</div>
</div>

</body>
</html>