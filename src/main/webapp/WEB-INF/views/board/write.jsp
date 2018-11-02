<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공부용 게시판 글작성 페이지</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/write.css">
<%@ include file="../include/import.jsp" %>
<script src="/resources/js/fileCheck.js"></script>
<script src="/resources/js/write.js"></script>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<div class="writeForm">
			<form id="writeForm" action="/board/write" method="post" enctype="multipart/form-data" id="writeForm">
				<div class="form-group">
			        <label for="writer">작성자</label>
			        <input type="text" class="form-control" id="writer" value="${loginInfo.name }" name="writer" readonly />
			    </div>
			
				<div class="form-group">
			        <label for="title">제목</label>
			        <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요." />
			    </div>
			    
				<div class="form-group">
					<label>내용 (이미지 파일은 Drag & Drop 형식으로 등록가능합니다.)</label>
					<div class="content" contentEditable="true"></div>
					<!-- <textarea style="display:none;" rows="20" cols="163" id="content" name="content"></textarea>  -->
					<input type="hidden" name="content" id="content" />
				</div> 
				
				<div class="board-original_image-path_list"></div>
				
				<div class="board-thumbnail_image-path_list"></div>
				
				<div class="form-group">
					<input type="file" name="files" style="margin-bottom: 15px;" />
					<input type="button" id="addFileForm" value="파일 폼 추가" />
					<input type="button" id="delFileForm" value="파일 폼 삭제" />
				</div>
				
				<input type="hidden" name="user_idx" value="${loginInfo.idx }" />
				
				<input type="submit" value="글작성" class="btn btn-sm" />
			</form>	
		</div>
	</div>
</div>

</body>
</html>