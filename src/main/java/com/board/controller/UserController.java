package com.board.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.board.domain.user.ProfileImg;
import com.board.domain.user.UserVO;
import com.board.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// 이메일 중복 체크
	@ResponseBody
	@RequestMapping(value = "/emailCheck", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String emailCheck(String email) throws Exception {
		boolean emailWhether = userService.emailCheck(email);
		
		if (!emailWhether) {
			return "이메일이 중복되지 않습니다.";
		}
		
		return "이메일이 중복됩니다.";
	}
	
	// 회원가입 폼
	@RequestMapping(value = "/singUp", method = RequestMethod.GET) 
	public void joinGET(Model model) throws Exception {
		model.addAttribute("page", "singUp");
	};
	
	// 회원가입 진행
	@RequestMapping(value = "/singUp", method = RequestMethod.POST)
	public String joinPOST(UserVO userVO, ProfileImg profileImg, RedirectAttributes rttr) throws Exception {
		userService.singUp(userVO, profileImg);
		
		rttr.addFlashAttribute("message", "회원가입 인증 이메일이 전송되었습니다. 이메일 인증을 진행해주세요.");
		return "redirect:/";
	}
	
	// 이메일 인증
	@RequestMapping(value = "/authEmail", method = RequestMethod.GET) 
	public String authEmail(String email, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		if (request.getSession().getAttribute("loginInfo") != null) {
			request.getSession().removeAttribute("loginInfo");
			request.getSession().removeAttribute("prfImgInfo");
		}
		
		userService.authEmail(email);
		
		rttr.addFlashAttribute("message", "이메일 인증이 완료되었습니다. 로그인 후 서비스를 이용하세요.");
		
		return "redirect:/";
	}
	
	// 로그인 폼
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(Model model) throws Exception {
		model.addAttribute("page", "login");
		logger.info("flashmap : " + model.asMap().get("msg"));
	};
	
	// 로그인 진행
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPOST(UserVO userVO, boolean useCookie, Model model, HttpSession session) throws Exception {
		boolean loginResult = userService.login(userVO.getEmail(), userVO.getPw());
		
		if (!loginResult) {
			model.addAttribute("loginInfo", null);
		} else {
			if (useCookie) {
				int keepTime = 60 * 60 * 24 * 7;
				
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * keepTime));
				
				userService.keepLogin(userVO.getEmail(), session.getId(), sessionLimit);
			}
			
			model.addAttribute("prfImgInfo", userService.prfImgInfo(userVO.getEmail()));
			model.addAttribute("loginInfo", userService.userInfo(userVO.getEmail()));
		}
	};
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest reqeust, HttpServletResponse response, HttpSession session, RedirectAttributes rttr) throws Exception {
		Cookie loginCookie = WebUtils.getCookie(reqeust, "keepLogin");
		
		if (loginCookie != null) {
			UserVO userVO =  (UserVO) session.getAttribute("loginInfo");
			
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
			userService.keepLogin(userVO.getEmail(), loginCookie.getValue(), new Date());
		}

		session.removeAttribute("loginInfo");
		session.removeAttribute("prfImgInfo");
		
		rttr.addFlashAttribute("message", "로그아웃 되었습니다.");
		
		return "redirect:/";
	}
	
}
