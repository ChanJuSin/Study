package com.board.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.util.upload.UploadFileUtils;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Resource(name = "boardFileUploadPath")
	private String boardFileUploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("page", "board");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGet(Model model) throws Exception {
		model.addAttribute("page", "board");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(String writer, String title, String content, String[] images, MultipartFile[] files, RedirectAttributes rttr) throws Exception {
		logger.info("writer : " + writer);
		logger.info("title : " + title);
		logger.info("content : " + content);
		
		for (String s: images) {
			logger.info(s.toString());
		}
		
		for (MultipartFile file : files) {
			logger.info("" + file.getOriginalFilename());
			logger.info("" + file.getSize());
			logger.info("" + file.getContentType());
			
			UploadFileUtils.uploadFile(boardFileUploadPath, file.getOriginalFilename(), file.getBytes());
		}
		
		rttr.addFlashAttribute("viewTag", content);
		
		return "redirect:/board/list";
	}
	
}
