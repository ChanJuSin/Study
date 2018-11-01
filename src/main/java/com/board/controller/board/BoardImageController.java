package com.board.controller.board;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.util.file.DisplayImage;
import com.board.util.file.UploadFileUtils;
import com.board.util.staticVariable.UploadPath;


@Controller
@RequestMapping("/board/image/")
public class BoardImageController {

	private static final Logger logger = LoggerFactory.getLogger(BoardImageController.class);
	
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
	
	// 프로필 이미지 리턴 컨트롤러 
	@ResponseBody
	@RequestMapping(value = "/displayBoardImage", method = RequestMethod.GET)
	public ResponseEntity <byte[]> displayBoardImage(String imagePath) throws Exception {
		return DisplayImage.displayImage(imagePath, UploadPath.BOARD_IMAGE_UPLOAD_PATH);
	}	
	
	
}
