<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공부용 게시판 상세보기 페이지</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/read.css">
<%@ include file="../include/import.jsp" %>
<script src="/resources/js/read.js"></script>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<main class="read-page">
			<section class="read-page_header">
				<div class="read-page_info">
					<img src="/upload/displayFile?filePath=${pageInfo.boardProfileImageVO.thumbnail_file_path }&distinction=profile" />
					<div class="read-page_user-info">
						<a href="#"><p>${pageInfo.boardVO.writer }</p></a>
						<span><fmt:formatDate value="${pageInfo.boardVO.crea_time }" pattern="yyyy-MM-dd hh:mm:dd"/></span>
					</div>
				</div>
				
				<c:if test="${not empty pageInfo.boardFileVO }">
					<div class="read-page_attachments">
						<input type="button" value="첨부파일(${fn:length(pageInfo.boardFileVO )})" />
						<div class="read-page_attachments-items">
							<div class="read-page_attachments-items_title">
								<span>첨부파일 목록</span>
								<input type="button" value="x" class="read-page_attachments-itmes_close btn btn-sm" />
							</div>
							<c:forEach items="${pageInfo.boardFileVO }" var="boardFileVO">
								<c:set var="file_path" value="${fn:length(boardFileVO.board_file_path) }" />
								<div class="read-page_attachments-item">
									<i class="fa fa-file-o"></i>
									<span class="read-page_attachment-name">${fn:substring(boardFileVO.board_file_path, 49, file_path) }</span>
									<a href="/upload/displayFile?filePath=${boardFileVO.board_file_path }&distinction=board">다운로드</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
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
		
		<c:if test="${loginInfo.idx == pageInfo.boardVO.user_idx }">
			<div class="page-control">
				<a href="/board/modify?idx=${pageInfo.boardVO.idx }&user_idx=${pageInfo.boardVO.user_idx}&writer=${pageInfo.boardVO.writer}" class="btn btn-sm">수정</a>
				<form method="post" action="/board/delete">
					<input type="hidden" name="board_idx" value="${pageInfo.boardVO.idx }" />
					<input type="hidden" name="user_idx" value="${pageInfo.boardVO.user_idx }" />
					<input type="hidden" name="writer" value="${pageInfo.boardVO.writer }" />
					<c:forEach items="${pageInfo.boardImageVO }" var="boardImageVO">
						<input type="hidden" name="images" value="${boardImageVO.board_image_file_path }" />
					</c:forEach>
					<c:forEach items="${pageInfo.boardFileVO }" var="boardFileVO">
						<input type="hidden" name="files" value="${boardFileVO.board_file_path }" />
					</c:forEach>
					<input type="submit" value="삭제" class="btn btn-sm" />
				</form>
			</div>
		</c:if>
	</div>
</div>

</body>
</html>