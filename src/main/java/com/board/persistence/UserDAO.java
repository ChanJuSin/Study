package com.board.persistence;

import java.util.Map;

import com.board.domain.user.ProfileImg;
import com.board.domain.user.UserVO;
import com.board.util.authEmail.AuthEmail;

public interface UserDAO {

	// 회원가입 여부 체크
	public boolean singUpCheck(String email);
	
	// 이메일 인증코드 발송
	public void sendAuthCode(AuthEmail authEmail) throws Exception;
	
	// 회원가입 진행
	public void singUp(UserVO userVO);
	
	// 프로필 이미지 등록
	public void profileImgUp(ProfileImg profileImg);
	
	// 이메일 인증
	public void accessAuthEmail(String email) throws Exception;
	
	// 로그인 처리 진행
	public boolean loginProgress(Map<String, String> loginMap) throws Exception;
	
	// 프로필 이미지 정보 얻음
	public ProfileImg getPrfImg(String email) throws Exception;
	
	// 유저 정보 얻음
	public UserVO getUserInfo(String email) throws Exception;
	
}
