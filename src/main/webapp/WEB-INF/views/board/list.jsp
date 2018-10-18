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
				    <tbody>
				        <tr>
				            <th scope="row" class="text-center">1</th>
				            <td class="text-center">신찬주</td>
				            <td>안녕하세요</td>
				            <td class="text-center">2018.10.17 11:10</td>
				            <td class="text-center">0</td>
				        </tr>
				        <tr>
				            <th scope="row" class="text-center">2</th>
				            <td class="text-center">홍길동</td>
				            <td>안녕하세요</td>
				            <td class="text-center">2018.10.17 11:10</td>
				            <td class="text-center">1</td>
				        </tr>
				         <tr>
				            <th scope="row" class="text-center">2</th>
				            <td class="text-center">김필</td>
				            <td>안녕하세요</td>
				            <td class="text-center">2018.10.17 11:10</td>
				            <td class="text-center">3</td>
				        </tr>
				    </tbody>
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