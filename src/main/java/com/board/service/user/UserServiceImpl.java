package com.board.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.user.ProfileImageVO;
import com.board.domain.user.UserVO;
import com.board.persistence.user.UserDAO;
import com.board.util.authEmail.AuthEmail;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	// 이메일 중복 체크
	@Override
	public boolean emailCheck(String email) throws Exception {
		return userDAO.singUpCheck(email);
	}

	// 회원 가입 진행 
	@Transactional
	@Override
	public void singUp(UserVO userVO, ProfileImageVO profileImageVO) throws Exception {
		userDAO.singUp(userVO);
		
		userDAO.profileImageRegister(profileImageVO);
		
		AuthEmail authEmail = new AuthEmail();
		String authHtml =  "<form action=http://localhost:8080/user/authEmail method=post>" 
					+ "	<input type=hidden value=" + userVO.getEmail() + " name=email />"
					+ " <input type=submit value=이메일인증 />" 
					+ "</form>";
		authEmail.setContent(authHtml);
		authEmail.setReceiver(userVO.getEmail());
		authEmail.setSubject("회원가입 이메일 인증");
		
		userDAO.sendAuthCode(authEmail);
	}

	// 이메일 인증 
	@Override
	public void authEmail(String email) throws Exception {
		userDAO.accessAuthEmail(email);
	}

	// 로그인 처리
	@Override
	public UserVO login(String email, String pw) throws Exception {
		Map<String, String> loginMap = new HashMap<>();
		loginMap.put("email", email);
		loginMap.put("pw", pw);
		
		return userDAO.loginProgress(loginMap);
	}

	// 프로필 이미지 정보 가져옴
	@Override
	public ProfileImageVO profileImageInfo(int idx) throws Exception {
		return userDAO.getProfileImage(idx);
	}
	
	// 로그인 유지
	@Override
	public void keepLogin(int idx, String sessionKey, Date sessionLimit) throws Exception {
		Map<String, Object> keppUserInfo = new HashMap<>();
		keppUserInfo.put("idx", idx);
		keppUserInfo.put("sessionKey", sessionKey);
		keppUserInfo.put("sessionLimit", sessionLimit);
		
		userDAO.keepLoginInfo(keppUserInfo);
	}

	// 로그인 유지 정보 가져옴
	@Override
	public UserVO getKeepLogin(String sessionKey) throws Exception {
		return userDAO.getKeepLoginInfo(sessionKey);
	}

}
