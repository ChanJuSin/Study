package com.board.persistence.user;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.user.ProfileImageVO;
import com.board.domain.user.UserVO;
import com.board.util.authEmail.AuthEmail;
import com.board.util.authEmail.SendAuthEmail;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private SendAuthEmail sendAuthEmail;
	
	private static final String namespace = "com.board.mapper.UserMapper";
	
	// 이메일 중복 체크
	@Override
	public boolean singUpCheck(String email) throws Exception {
		String returnId = sqlSession.selectOne(namespace + ".singUpCheck", email);
		
		if (returnId != null) {
			return true;
		}
		
		return false;
	}
	
	// 이메일 인증코드 발송
	@Override
	public void sendAuthCode(AuthEmail authEmail) throws Exception {
		sendAuthEmail.sendAuthCode(authEmail);
	}

	// 회원가입 진행
	@Override
	public void singUp(UserVO userVO) throws Exception {
		sqlSession.insert(namespace + ".singUp", userVO);
	}
	
	// 프로필 이미지 등록
	@Override
	public void profileImageRegister(ProfileImageVO profileImageVO) throws Exception {
		sqlSession.insert(namespace + ".profileImageRegister", profileImageVO);
	}

	// 이메일 인증
	@Override
	public void accessAuthEmail(String email) throws Exception {
		sqlSession.update(namespace + ".accessAuthEmail", email);
	}

	// 로그인 처리 진행
	@Override
	public boolean loginProgress(Map<String, String> loginMap) throws Exception {
		String returnPw = sqlSession.selectOne(namespace + ".loginProgress", loginMap);
		
		if (returnPw == null) {
			return false;
		}
		
		return true;
	}

	// 프로필 이미지 정보 얻음
	@Override
	public ProfileImageVO getPrfImg(String email) throws Exception {
		return sqlSession.selectOne(namespace + ".getPrfImg", email);
	}
	
	// 로그인 정보 유지
	@Override
	public void keepLoginInfo(Map<String, Object> keppUserInfo) throws Exception {
		sqlSession.update(namespace + ".keepLoginInfo", keppUserInfo);
	}
	
	// 로그인 유지 정보 가져옴
	@Override
	public UserVO getKeepLoginInfo(String sessionKey) throws Exception {
		return sqlSession.selectOne(namespace + ".getKeepLoginInfo", sessionKey);
	}

	// 유저 정보 얻음
	@Override
	public UserVO getUserInfo(String email) throws Exception {
		return sqlSession.selectOne(namespace + ".getUserInfo", email);
	}

}
