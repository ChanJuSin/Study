package com.board.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

public class CommonException {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonException.class);

	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		logger.error(ex.getMessage());
		model.addAttribute("error", ex);
		return "/error/error";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String hanlde404(NoHandlerFoundException ex) {
		logger.error(ex.getMessage());
		return "/error/error404";
	}
	
}
