package com.board.service.user;

import java.util.Date;

import com.board.domain.user.ProfileImageVO;
import com.board.domain.user.UserVO;

public interface UserService {
	
	// 이메일 중복 체크 
	public boolean emailCheck(String email) throws Exception;

	// 회원 가입 진행 
	public void singUp(UserVO userVO, ProfileImageVO profileImageVO) throws Exception;
	
	// 이메일 인증 
	public void authEmail(String email) throws Exception;
	
	// 로그인 처리
	public boolean login(String email, String pw) throws Exception;
	
	// 프로필 이미지 정보 가져옴
	public ProfileImageVO prfImgInfo(String email) throws Exception;
	
	// 로그인 유지
	public void keepLogin(String email, String sessionKey, Date sessionLimit) throws Exception;
	
	// 로그인 유지 정보 가져옴
	public UserVO getKeepLogin(String sessionKey) throws Exception;
	
	// 로그인 처리 성공시 유저 정보 가져옴
	public UserVO userInfo(String email) throws Exception;
	
}
