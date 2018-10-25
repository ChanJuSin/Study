package com.board.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.board.domain.board.BoardVO;
import com.board.domain.user.UserVO;

public class BoardWriterAuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (request.getMethod().equals("GET")) {
			HttpSession session = request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("loginInfo");
			
			ModelMap modelMap = modelAndView.getModelMap();
			
			Map<String, Object> pageInfo = (Map<String, Object>) modelMap.get("pageInfo");
			
			BoardVO boardVO = (BoardVO) pageInfo.get("boardVO");
			
			FlashMap flashMap = new FlashMap();
			
			if (userVO == null) {
				flashMap.put("loginCheckMsg", "잘못된 접근 입니다.");
				FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
				flashMapManager.saveOutputFlashMap(flashMap, request, response);
				response.sendRedirect("/");
			}
			
			if ((userVO != null) && (boardVO != null)) {
				if (boardVO.getUser_idx() != userVO.getIdx()) {
					flashMap.put("loginCheckMsg", "잘못된 접근 입니다.");
					
					FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
					flashMapManager.saveOutputFlashMap(flashMap, request, response);
					
					response.sendRedirect("/");
				}
			}
		}
	}
	
}
