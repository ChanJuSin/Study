package com.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.board.BoardVO;
import com.board.service.board.BoardService;
import com.board.util.upload.UploadFileUtils;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Resource(name = "boardFileUploadPath")
	private String boardFileUploadPath;
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("page", "board");
		model.addAttribute("list", boardService.list());
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGet(Model model) throws Exception {
		model.addAttribute("page", "board");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(BoardVO board, String[] images, MultipartFile[] files, RedirectAttributes rttr) throws Exception {
		logger.info("writer : " + board.getWriter());
		logger.info("title : " + board.getTitle());
		logger.info("content : " + board.getContent());
		
		List<String> boardFilePathList = new ArrayList<>();
		
		if (files != null) {
			for (MultipartFile file : files) {
				logger.info("" + file.getOriginalFilename());
				logger.info("" + file.getSize());
				logger.info("" + file.getContentType());
				
				String boardFilePath = UploadFileUtils.uploadFile(boardFileUploadPath, file.getOriginalFilename(), file.getBytes());
				
				boardFilePathList.add(boardFilePath);
			}
		}
		
		boardService.write(board, images, boardFilePathList);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(int idx, String writer, Model model) throws Exception {
		model.addAttribute("pageInfo", boardService.read(idx, writer));
		model.addAttribute("page", "board");
	}
	
}
