package com.board.controller.board;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.board.util.upload.DeleteFile;
import com.board.util.upload.UploadFileUtils;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Resource(name = "boardFileUploadPath")
	private String boardFileUploadPath;
	
	@Resource(name = "boardImgUploadPath")
	private String boardImgUploadPath;
	
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
	public String writePost(BoardVO boardVO, String[] images, MultipartFile[] files, RedirectAttributes rttr) throws Exception {
		logger.info("writer : " + boardVO.getWriter());
		logger.info("title : " + boardVO.getTitle());
		logger.info("content : " + boardVO.getContent());
		logger.info("user_idx : " + boardVO.getUser_idx());
		
		List<String> boardFilePathList = new ArrayList<>();
		
		if (files != null) {
			for (MultipartFile file : files) {
				logger.info("" + file.getOriginalFilename());
				logger.info("" + file.getSize());
				logger.info("" + file.getContentType());
				
				if (file.getSize() > 0) {
					String boardFilePath = UploadFileUtils.uploadFile(boardFileUploadPath, file.getOriginalFilename(), file.getBytes());
					
					boardFilePathList.add(boardFilePath);
				}
			}
		}
		
		boardService.write(boardVO, images, boardFilePathList);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(int idx, int user_idx, String writer, Model model) throws Exception {
		model.addAttribute("pageInfo", boardService.read(idx, user_idx, writer));
		model.addAttribute("page", "board");
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(int board_idx, int user_idx, String writer, @RequestParam(value="images" ,required=false, defaultValue="") String[] images,
	@RequestParam(value="files", required=false, defaultValue="") String[] files) throws Exception {
		boardService.delete(board_idx, user_idx, writer, images, files, boardImgUploadPath, boardFileUploadPath);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int idx, int user_idx, String writer, Model model, ModelMap modelMap) throws Exception {
		model.addAttribute("pageInfo", boardService.read(idx, user_idx, writer));
		model.addAttribute("page", "board");
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyDeleteFile", method = RequestMethod.POST)
	public String modifyDeleteFile(int idx, String writer, String filePath) throws Exception {
		DeleteFile.deleteFile(boardFileUploadPath, filePath);
		boardService.modifyDeleteFile(idx, writer, filePath);
		return "1";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST) 
	public String modifyPOST(int idx, int user_idx, String writer, String title,  String content, MultipartFile[] files, @RequestParam(value="addImages", required=false, defaultValue="") String[] addImages,
	@RequestParam(value="deleteImages", required=false, defaultValue="") String[] deleteImages) throws Exception {
		boardService.modify(idx, user_idx, writer, title, content, files, addImages, deleteImages, boardImgUploadPath, boardFileUploadPath);
		return "redirect:/";
	}
	
}
