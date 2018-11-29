package com.board.controller.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.service.board.BoardService;
import com.board.util.file.FileRelatedUtils;
import com.board.util.file.UploadFileUtils;
import com.board.util.staticVariable.UploadPath;


@Controller
@RequestMapping("/board/image/")
public class BoardImageController {

	@Autowired
	private BoardService boardServiceImpl;
	
	// 게시글 이미지 업로드
	@ResponseBody
	@RequestMapping(value = "/uploadBoardImage", method = RequestMethod.POST)
	public List<String> uploadBoardImage(MultipartFile files[]) throws Exception {
		List<String> imagePaths = new ArrayList<>();
		
		for (MultipartFile file: files) {
			String imagePath = UploadFileUtils.uploadFile(UploadPath.BOARD_IMAGE_UPLOAD_PATH, file.getOriginalFilename(), file.getBytes());
			imagePath = imagePath.substring(0, 12) + imagePath.substring(14);
			imagePaths.add(imagePath);
		}
		
		return imagePaths;
	}
	
	// 게시글 이미지 리턴 컨트롤러 
	@ResponseBody
	@RequestMapping(value = "/displayBoardImage", method = RequestMethod.GET)
	public ResponseEntity <byte[]> displayBoardImage(String imagePath) throws Exception {
		return FileRelatedUtils.displayImage(imagePath, UploadPath.BOARD_IMAGE_UPLOAD_PATH);
	}	
	
	// 게시글 파일 다운로드
	@ResponseBody
	@RequestMapping(value = "/downloadBoardFile", method = RequestMethod.GET)
	public void downloadBoardFile(String filePath) throws Exception {
		
	}
	
	// 게시글 이미지 파일 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteBoardFile", method = RequestMethod.POST) 
	public String deleteBoardFile(String board_idx, String user_idx, String[] originalImagePaths, String[] thumbnailImagePaths) throws Exception {
		boardServiceImpl.modifyDeleteImage(board_idx, user_idx, originalImagePaths, thumbnailImagePaths);
		return "게시글 이미지가 삭제되었습니다.";
	}
	
}
