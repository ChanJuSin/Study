<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">공부용 게시판 홈</a>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item <c:out value="${page eq 'board' ? 'active' : '' }"/>">
            	<a class="nav-link" href="/board/list">게시판</a>
            </li>
            
            <c:choose>
            	<c:when test="${not empty loginInfo}">
            		<li class="nav-item <c:out value="${page eq 'myPage' ? 'active' : '' }"/>">
			            <a class="nav-link" href="/user/myPage">마이페이지</a>
			        </li>
            	
            		<li class="nav-item">
			            <a class="nav-link" href="/user/logout">로그아웃</a>
			        </li>
            	</c:when>

				<c:otherwise>
					<li class="nav-item <c:out value="${page eq 'login' ? 'active' : '' }"/>">
			            <a class="nav-link" href="/user/login">로그인</a>
			        </li>				
			        
			        <li class="nav-item <c:out value="${page eq 'singUp' ? 'active' : '' }"/>">
               			<a class="nav-link" href="/user/singUp">회원가입</a>
           			</li>	
				</c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>