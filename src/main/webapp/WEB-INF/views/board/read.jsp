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
		<div class="page">
			<div class="profile_box">
				<img width="100px" height="100px"/>
				
				<a href="#" class="user_name">서브웨이</a>
				<p>2018-10-19 15:24</p>
			</div>
			
			<div class="page_box">
				<div class="page_header">
					<p class="page_idx">7</p>
					<p class="page_title">테스트용 게시글 입니다.</p>
					
					<div class="Attachments">
						<c:forEach items="${pageInfo.fileInfo }" var="file">
							<c:set value="${fn:length(file.board_file_path)}" var="board_file_path_last" />
							<a href="#">${fn:substring(file.board_file_path, 50, board_file_path_last)}</a>
						</c:forEach>
					</div>
				</div>
				
				<div class="page_body">
					<div class="page_content">
						${pageInfo.boardVO.content }
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>