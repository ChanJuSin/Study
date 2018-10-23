package com.board.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import com.board.domain.user.ProfileImgVO;
import com.board.domain.user.UserVO;
import com.board.service.user.UserService;

public class KeppLoginAuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		Cookie loginCookie = WebUtils.getCookie(request, "keepLogin");
		
		if (loginCookie != null) {
			UserVO userVO = userService.getKeepLogin(loginCookie.getValue());
			
			if (userVO != null) {
				HttpSession session = request.getSession();
				
				ProfileImgVO profileImg = userService.prfImgInfo(userVO.getEmail());
				
				
				session.setAttribute("loginInfo", userVO);
				session.setAttribute("prfImgInfo", profileImg);
			}
		}
	}
	
}
