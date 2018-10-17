<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공부용 게시판 로그인 페이지</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/login.css">
<%@ include file="../include/import.jsp" %>
<script src="/resources/js/login.js"></script>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<div class="loginForm">
			<form method="post" action="/user/login" onsubmit="return checkForm();">
				<div class="form-group">
			        <label for="email">이메일</label>
			        <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요." />
			    </div>
		
			    <div class="form-group">
			        <label for="pw">비밀번호</label>
					<input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력하세요." />
			    </div>
			    
			    <div>
			    	<label for="loginRemember">로그인 정보 기억하기</label>
					<input type="checkbox" name="useCookie" />
			    </div>
			   
				<input type="submit" value="로그인" class="btn btn-sm" />
				<input type="submit" value="비밀번호 찾기" class="btn btn-sm" />
			</form>	
		</div>
	</div>
</div>

<script>
	let loginResult = "${loginResult}";
	
	if (loginResult === "false") {
		alert("아이디 또는 비밀번호가 일치하지 않습니다.");
		loginResult = null;
	}
</script>

</body>
</html>