package com.board.controller.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.board.BoardVO;
import com.board.service.board.BoardService;
import com.board.util.file.FileRelatedUtils;
import com.board.util.file.UploadFileUtils;
import com.board.util.staticVariable.UploadPath;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
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
	public String writePost(BoardVO boardVO, @Nullable String[] board_original_image_paths, @Nullable String[] board_thumbnail_image_paths, @Nullable MultipartFile files[], RedirectAttributes rttr) throws Exception {
		logger.info("writer : " + boardVO.getWriter());
		logger.info("title : " + boardVO.getTitle());
		logger.info("content : " + boardVO.getContent());
		logger.info("user_idx : " + boardVO.getUser_idx());
		
		logger.info("board_original_image_path : " + board_original_image_paths);
		logger.info("board_thumbnail_image_path : " + board_thumbnail_image_paths);
		logger.info("files : " + files);
		
		if ((board_original_image_paths != null) && (board_thumbnail_image_paths != null)) {
			for(String o: board_original_image_paths) {
				logger.info("original : " + o);
			}
			
			for(String t: board_thumbnail_image_paths) {
				logger.info("thumbnail : " + t);
			}
		}
		
		List<String> boardFilePathList = new ArrayList<>();
		
		if (files != null) {
			for (MultipartFile file: files) {
				if (file.getSize() > 0) {
					logger.info("file originalName : " + file.getOriginalFilename());
					logger.info("file contentType : " + file.getContentType());
					logger.info("file size : " + file.getSize());
					
					String boardFilePath = UploadFileUtils.uploadFile(UploadPath.BOARD_FILE_UPLOAD_PATH, file.getOriginalFilename(), file.getBytes());
					boardFilePathList.add(boardFilePath);
				}
			}
		}
		
		boardService.write(boardVO, board_original_image_paths, board_thumbnail_image_paths, boardFilePathList);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(BoardVO boardVO, Model model, HttpServletResponse response) throws Exception {
		/*Cookie cookie = new Cookie("clientTime", "");
		cookie.setMaxAge(60 * 60);
		response.addCookie(cookie);*/
				
		model.addAttribute("pageInfo", boardService.read(boardVO));
		model.addAttribute("page", "board");
		return "/board/read";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(int board_idx, int user_idx, String writer, @RequestParam(value="images" ,required=false, defaultValue="") String[] images,
	@RequestParam(value="files", required=false, defaultValue="") String[] files) throws Exception {
		boardService.delete(board_idx, user_idx, writer, images, files, UploadPath.BOARD_IMAGE_UPLOAD_PATH, UploadPath.BOARD_FILE_UPLOAD_PATH);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(BoardVO boardVO, Model model, ModelMap modelMap) throws Exception {
		model.addAttribute("pageInfo", boardService.read(boardVO));
		model.addAttribute("page", "board");
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyDeleteFile", method = RequestMethod.POST)
	public String modifyDeleteFile(int idx, String writer, String filePath) throws Exception {
		FileRelatedUtils.deleteFile(UploadPath.BOARD_FILE_UPLOAD_PATH, filePath);
		boardService.modifyDeleteFile(idx, writer, filePath);
		return "1";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST) 
	public String modifyPOST(int idx, int user_idx, String writer, String title,  String content, MultipartFile[] files, @RequestParam(value="addImages", required=false, defaultValue="") String[] addImages,
	@RequestParam(value="deleteImages", required=false, defaultValue="") String[] deleteImages) throws Exception {
		boardService.modify(idx, user_idx, writer, title, content, files, addImages, deleteImages, UploadPath.BOARD_IMAGE_UPLOAD_PATH, UploadPath.BOARD_FILE_UPLOAD_PATH);
		return "redirect:/";
	}
	
}
