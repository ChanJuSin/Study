<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공부용 게시판 글작성 페이지</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<%@ include file="../include/import.jsp" %>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<link href="/resources/dist/summernote.css" rel="stylesheet">
<script src="/resources/dist/summernote.js"></script>
<script src="/resources/dist/lang/summernote-ko-KR.js"></script>
<script src="/resources/js/fileCheck.js"></script>
<script src="/resources/js/write.js"></script>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<div class="writeForm">
			<form method="post" action="/board/write" onsubmit="return checkForm();">
				<div class="form-group">
			        <label for="writer">작성자</label>
			        <input type="text" class="form-control" id="writer" value="${loginInfo.name }" name="writer" readonly />
			    </div>
			
				<div class="form-group">
			        <label for="title">제목</label>
			        <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요." />
			    </div>
			    
				<div class="content">
					<label>내용</label>
					<div id="summernote"></div>
				</div>
				
				<input type="submit" value="글작성" class="btn btn-sm" />
			</form>	
		</div>
	</div>
</div>

</body>
</html>