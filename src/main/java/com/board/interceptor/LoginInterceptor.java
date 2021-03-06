package com.board.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (request.getMethod().equals("GET")) {
			logger.info("login get");
		} else {
			logger.info("login post");
			
			HttpSession session = request.getSession();
			
			ModelMap modelMap = modelAndView.getModelMap();
			Object userVO = modelMap.get("loginInfo");
			Object profileImageInfo = modelMap.get("profileImageInfo");
			
			FlashMap flashMap = new FlashMap();
			FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
			flashMapManager.saveOutputFlashMap(flashMap, request, response);
			
			if (userVO == null) {
				logger.info("아이디 또는 비밀번호가 일치하지 않습니다.");
				
				flashMap.put("loginResult", false);
				flashMapManager.saveOutputFlashMap(flashMap, request, response);
			
				response.sendRedirect("/user/login");
			} else {
				if (request.getParameter("useCookie") != null) {
					logger.info("로그인 정보 유지");
					Cookie loginCookie = new Cookie("keepLogin", session.getId());
					loginCookie.setPath("/");
					loginCookie.setMaxAge(60 * 60 * 24 * 7);
					response.addCookie(loginCookie);
				}
				
				logger.info("로그인이 되었습니다.");
				session.setAttribute("loginInfo", userVO);
				session.setAttribute("profileImageInfo", profileImageInfo);
				
				flashMap.put("loginResult", true);
				flashMapManager.saveOutputFlashMap(flashMap, request, response);
				
				response.sendRedirect("/");
			}
		}
	}
	
}
