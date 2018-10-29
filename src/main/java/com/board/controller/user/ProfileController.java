package com.board.controller.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.user.ProfileImageVO;
import com.board.util.staticVariable.UploadPath;
import com.board.util.upload.DeleteFile;
import com.board.util.upload.MediaUtils;
import com.board.util.upload.UploadFileUtils;

@Controller
@RequestMapping("/user/profile/*")
public class ProfileController {
	
	// 프로필 이미지 업로드 컨트롤러
	@ResponseBody
	@RequestMapping(value = "/uploadProfileImage", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		return new ResponseEntity<>(UploadFileUtils.uploadFile(UploadPath.PROFILE_IMAGE_UPLOAD_PATH, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);	
	}

	// 프로필 이미지 삭제 컨트롤러
	@ResponseBody
	@RequestMapping(value = "/deleteProfileImage", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8") 
	public ResponseEntity<String> deleteProfileImage(@RequestBody ProfileImageVO profileImageVO) throws Exception {
		DeleteFile.deleteFile(UploadPath.PROFILE_IMAGE_UPLOAD_PATH, profileImageVO.getOriginal_image_path());
		DeleteFile.deleteFile(UploadPath.PROFILE_IMAGE_UPLOAD_PATH, profileImageVO.getThumbnail_image_path());
		return new ResponseEntity<>("이미지가 삭제되었습니다.", HttpStatus.OK);
	}
	
	// 프로필 이미지 리턴 컨트롤러 
	@ResponseBody
	@RequestMapping(value = "/displayProfileImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayProfileImage(@RequestParam(required=false, defaultValue="\\default\\defaultImg.jpg") String imagePath) throws Exception {
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		imagePath = imagePath.replace('/', File.separatorChar);
		
		String imageType = imagePath.substring(imagePath.lastIndexOf(".") + 1);
		
		headers.setContentType(MediaUtils.getMediaType(imageType));
		
		try (InputStream in = new FileInputStream(UploadPath.PROFILE_IMAGE_UPLOAD_PATH + imagePath)) {
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			throw e;
		}
		
		return entity;
	}
	
}
