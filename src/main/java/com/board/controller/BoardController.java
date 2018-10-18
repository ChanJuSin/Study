package com.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		model.addAttribute("page", "board");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGet(Model model) {
		model.addAttribute("page", "board");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(String writer, String title, String content, String[] images, MultipartFile files, RedirectAttributes rttr) {
		logger.info("writer : " + writer);
		logger.info("title : " + title);
		logger.info("content : " + content);
		
		for (String s: images) {
			logger.info(s.toString());
		}
		
		logger.info("" + files.getOriginalFilename());
		logger.info("" + files.getSize());
		logger.info("" + files.getContentType());
		
		rttr.addFlashAttribute("viewTag", content);
		
		return "redirect:/board/list";
	}
	
}
