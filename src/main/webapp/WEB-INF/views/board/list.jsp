<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공부용 게시판 목록</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/list.css">
<%@ include file="../include/import.jsp" %>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<div class="board">
			<div class="board_list">
				<table class="table table-bordered">
				    <thead>
				        <tr>
				        	<th scope="col" class="text-center">번호</th>
				            <th scope="col" class="text-center">작성자</th>
				            <th scope="col" class="text-center">제목</th>
				            <th scope="col" class="text-center">작성일</th>
				            <th scope="col" class="text-center">조회</th>
				        </tr>
				    </thead>
				<c:forEach items="${list }" var="boardVO">
					<tbody>
					    <tr>
					        <th scope="row" class="text-center">${boardVO.idx }</th>
					        <td class="text-center">${boardVO.writer }</td>
					        <td>
					        	<a href="/board/read?idx=${boardVO.idx }&user_idx=${boardVO.user_idx }">${boardVO.title }</a>
					        	<c:if test="${boardVO.file_whether }"><i class="fa fa-file-o"></i></c:if>
					        	<c:if test="${boardVO.image_whether }"><i class="fa fa-file-image-o"></i></c:if>
					        	<c:if test="${boardVO.video_whether }"><i class="fa fa-video-camera"></i></c:if>
					        </td>
					        <td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.crea_time }"/></td>
					        <td class="text-center">${boardVO.views }</td>
					    </tr>
					</tbody>
				</c:forEach>
				</table>		
			</div>
			
			<c:if test="${not empty loginInfo }">
				<input type="button" value="글작성" class="board_write btn btn-sm" onclick="location.href='/board/write'" /> 
			</c:if>
			
			<div class="board_pagination">
				<nav>
				    <ul class="pagination">
				        <li class="page-item">
				            <a class="page-link" href="#" aria-label="Previous">
				                <span aria-hidden="true">&laquo;</span>
				                <span class="sr-only">Previous</span>
				            </a>
				        </li>
				        <li class="page-item"><a class="page-link" href="#">1</a></li>
				        <li class="page-item"><a class="page-link" href="#">2</a></li>
				        <li class="page-item"><a class="page-link" href="#">3</a></li>
				        <li class="page-item">
				            <a class="page-link" href="#" aria-label="Next">
				                <span aria-hidden="true">&raquo;</span>
				                <span class="sr-only">Next</span>
				            </a>
				        </li>
				    </ul>
				</nav>			
			</div>
		</div>
	</div>
</div>

<c:if test="${not empty viewTag }">
	${viewTag }
</c:if>

</body>
</html>