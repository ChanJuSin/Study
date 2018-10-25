package com.board.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.service.user.UserService;
import com.board.util.authEmail.SendAuthEmail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SendAuthEmailTest {

	@Autowired
	private SendAuthEmail sendAuthEmail;
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(SendAuthEmailTest.class);
	
	private static final String namespace = "com.board.mapper.UserMapper";
	
	/*@Test
	public void sendTest() throws Exception {
		long start = System.currentTimeMillis();
		
		AuthEmail authEmail = new AuthEmail();
		authEmail.setContent("<a href=" + "http://localhost:8080" + ">홈</a>");
		authEmail.setReceiver("ckswn1259@naver.com");
		authEmail.setSubject("회원가입 인증번호");
		sendAuthEmail.sendAuthCode(authEmail);
		
		long end = System.currentTimeMillis();
		
		logger.info("실행 시간 : " + ( end - start )/1000.0 + "초");
	}*/
	
	@Test
	public void test() throws Exception {
		String test = "/2018/10/25/30bcba4e-fb43-4992-bb93-e88df171ab89_dog.gif/";
		String[] list = test.split("\\.");
		
		System.out.println(list[0] + "." + list[1].substring(0, 3));
	}
	
}
