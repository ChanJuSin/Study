<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>공부용 게시판 회원가입 페이지</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/singUp.css">
<%@ include file="../include/import.jsp" %>
<script src="/resources/js/singUp.js"></script>
<script src="/resources/js/fileCheck.js"></script>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<div class="singUpForm">
			<form method="post" action="/user/singUp" onsubmit="return checkForm();">
				<div class="form-group">
			        <label for="email">이메일</label>
			        <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요." />
			    </div>
			    
			    <input type="button" value="이메일 중복체크" class="btn btn-sm email_check" style="margin-bottom: 15px;" />
			    
			    <div class="form-group">
			        <label for="pw">비밀번호</label>
					<input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력하세요." />
			    </div>
			    
			    <div class="form-group">
			    	<label for="name">이름</label>
					<input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요." />
			    </div>
			    
			   <div class="form-group">
				   <label for="profile_image">프로필 이미지</label>
				   <div class="profile_image_drop">
					   <small id="profile_image_help1" class="form-text">등록하실 이미지를 범위안에 드래그 앤 드롭 해주세요. (이미지 파일만 등록 가능합니다.)</small>
					   <small id="profile_image_help2" class="form-text">프로필 이미지를 선택하지 않으시면 기본이미지가 등록됩니다.</small>	
				   </div>
			   </div>
			   
			   <div class="profile_image_sumnail">
			   		<div>
			   		<label>프로필 이미지 썸네일</label>	
			   		</div>
			   		<img class="profileImg" src="/upload/displayFile?distinction=profile" style="height:100px; width: 100px;">
			   </div>
			   
				<input type="submit" value="회원가입" class="btn btn-sm" />
			</form>	
		</div>
	</div>
</div>

</body>
</html>