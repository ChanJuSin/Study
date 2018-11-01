<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>공부용 게시판 홈</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/home.css">
<%@ include file="include/import.jsp" %>
</head>
<body>

<div class="container-fluid">
	<%@ include file="include/header.jsp" %>
	<div class="container">
		<c:if test="${not empty message}">
			<div class="alert alert-secondary" role="alert">${message }</div>
		</c:if>
		
		<c:if test="${not empty loginInfo}">
			<c:if test="${not loginInfo.auth_wt }">
				<div class="alert alert-secondary" role="alert">이메일 인증이 진행되지 않았습니다. 이메일 인증을 진행해주세요.</div>
			</c:if>
		
			<div class="profile">
				<div class="profile_image_sumnail">
					<img class="profileImg" src="/user/profile/displayProfileImage?imagePath=${profileImageInfo.thumbnail_image_path }" width="100px" height="100px">
					<a href="/user/myPage">내정보 수정</a>
				</div>
				
				<h6>${loginInfo.email}님 환영합니다.</h6>
				<h6>프로필 이미지 등록 날짜 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${profileImageInfo.register_image_time }"/></h6>
			</div>
		</c:if>
	</div>
</div>

<script>
	let loginResult = "${loginResult}";
	
	if (loginResult === "true") {
		alert("로그인에 성공했습니다.");
		loginResult = null;
	}
	
	let loginCheckMsg = "${loginCheckMsg}";
	
	if (loginCheckMsg) {
		alert(loginCheckMsg);
	}
</script>

</body>
</html>