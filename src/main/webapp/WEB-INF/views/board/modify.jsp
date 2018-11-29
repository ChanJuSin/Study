<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공부용 게시판 수정 페이지</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/write.css">
<link rel="stylesheet" href="/resources/css/modify.css">
<%@ include file="../include/import.jsp" %>
<script src="/resources/js/fileCheck.js"></script>
<script src="/resources/js/modify.js"></script>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<div class="writeForm">
			<form id="modifyForm" action="/board/modify" method="post" enctype="multipart/form-data" id="writeForm">
				<div class="form-group">
			        <label for="writer">작성자</label>
			        <input type="text" class="form-control" id="writer" value="${pageInfo.boardVO.writer }" name="writer" readonly />
			    </div>
			
				<div class="form-group">
			        <label for="title">제목</label>
			        <input type="text" class="form-control" id="title" name="title" value="${pageInfo.boardVO.title }" placeholder="제목을 입력하세요." />
			    </div>
			    
				<div class="form-group">
					<label>내용 (이미지 파일은 Drag & Drop 형식으로 등록가능합니다.)</label>
					<input type="button" class="add-youtube_video" value="유튜브 영상 추가" />
					<div class="delete-youtube_videos-lists"></div>
					<div class="content" contentEditable="true">${pageInfo.boardVO.content }</div>
					<c:if test="${not empty pageInfo.boardFileVO }">
						<div class="modify-page_attachments">
							<input type="hidden" class="target_attachments_length" value="${fn:length(pageInfo.boardFileVO )}" />
							<input type="button" class="attachments_length" value="첨부파일(${fn:length(pageInfo.boardFileVO )})" />
							<div class="modify-page_attachments-items">
							    <div class="modify-page_attachments-items_title">
							        <span>첨부파일 목록</span>
							        <input type="button" value="x" class="modify-page_attachments-itmes_close btn btn-sm" />
							    </div>
							    <c:forEach items="${pageInfo.boardFileVO }" var="boardFileVO">
							        <c:set var="file_path" value="${fn:length(boardFileVO.board_file_path) }" />
							        <div class="modify-page_attachments-item">
							            <i class="fa fa-file-o"></i>
							            <span class="modify-page_attachment-name">${fn:substring(boardFileVO.board_file_path, 49, file_path) }</span>
							            <input type="hidden" value="${boardFileVO.board_file_path }" />
							            <input type="button" value="삭제" class="deleteFile btn btn-sm" />
							        </div>
							    </c:forEach>
							</div>						
						</div>			
					</c:if>
					<input type="hidden" name="content" id="content" />
				</div> 
				
				<div class="form-group">
					<input type="file" name="files" style="margin-bottom: 15px;" />
					<input type="button" id="addFileForm" value="파일 폼 추가" />
					<input type="button" id="delFileForm" value="파일 폼 삭제" />
				</div>
				
				<div class="board-youtube_viedo-path_list"></div>
				<div class="board-youtube_video-modify-delete_list"></div>
				
				<input type="hidden" id="idx" name="idx" value="${pageInfo.boardVO.idx }" />
				<input type="hidden" id="user_idx" name="user_idx" value="${loginInfo.idx }" />
				
				<input type="submit" value="글수정" class="modifyBtn btn btn-sm" />
			</form>	
		</div>
	</div>
</div>

<c:if test="${not empty pageInfo.boardImageVO }">
	<% 
		List<String> imageFilePaths = new ArrayList<>();
	%>
	<c:forEach items="${pageInfo.boardImageVO }" var="boardImageVO" varStatus="status">
		<c:set var="imageFilePath" value="${boardImageVO.board_original_image_path }" />
		<%
			imageFilePaths.add(pageContext.getAttribute("imageFilePath").toString());
		%>
		<c:if test="${status.last }">
			<%
				pageContext.setAttribute("imageFilePaths", imageFilePaths);
			%>
			<script>
				let imageFilePaths = "${imageFilePaths}";
				imageFilePaths = imageFilePaths.replace("[", "");
				imageFilePaths = imageFilePaths.replace("]", "");
				imageFilePaths = imageFilePaths.split(",");
				for (let i = 0; i < imageFilePaths.length; i++) {
					imageFilePaths[i] = imageFilePaths[i].trim();
				}
				addDeleteImageTag(imageFilePaths);
			</script>
		</c:if>
	</c:forEach>
</c:if> 

<c:if test="${not empty pageInfo.boardVideoVO }">
	<%
		List<String> videoPaths = new ArrayList<>();
	%>
	<c:forEach items="${pageInfo.boardVideoVO }" var="boardVideoVO" varStatus="status" >
		<c:set var="videoPath" value="${boardVideoVO.video_path }" />
		<%
			videoPaths.add(pageContext.getAttribute("videoPath").toString());
		%>
		<c:if test="${status.last }">
			<%
				pageContext.setAttribute("videoPaths", videoPaths);
			%>
			<script>
				let videoPaths = "${videoPaths}";
				videoPaths = videoPaths.replace("[", "");
				videoPaths = videoPaths.replace("]", "");
				videoPaths = videoPaths.split(",");
				for (let i = 0; i < videoPaths.length; i++) {
					videoPaths[i] = videoPaths[i].trim();
				}
				addDeleteVideoTag(videoPaths);
			</script>
		</c:if>
	</c:forEach>
</c:if>


</body>
</html>