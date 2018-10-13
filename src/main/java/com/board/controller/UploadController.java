package com.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.util.upload.MediaUtils;
import com.board.util.upload.UploadFileUtils;

@Controller
@RequestMapping("/upload/*")
public class UploadController {
	
	@Resource(name = "profileImgUploadPath") 
	private String profileImgUploadPath;

	// 파일 업로드 컨트롤러
	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		return new ResponseEntity<>(UploadFileUtils.uploadFile(profileImgUploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	
	// 파일 이미지 / 다운로드 컨트롤러
	@ResponseBody
	@RequestMapping(value = "/displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(@RequestParam(required=false, defaultValue="/default/defaultImg.jpg") String filePath, String distinction) throws Exception {
		ResponseEntity<byte[]> entity = null;
		
		if (distinction.equals("profile")) {
			if (filePath.equals("/default/defaultImg.jpg")) {
				entity = displayDefaultImg(profileImgUploadPath, filePath.replace('/', File.separatorChar));
			} else {
				entity = displayFileProcess(profileImgUploadPath, filePath.replace('/', File.separatorChar));
			}
		} else {
			
		}
		
		return entity;
	}
	
	// 파일 이미지, 다운로드 
	private static ResponseEntity<byte[]> displayFileProcess(String uploadPath, String filePath) throws Exception  {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> entity;
		
		try (InputStream in = new FileInputStream(uploadPath + filePath)) {
			String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
			
			MediaType mType = MediaUtils.getMediaType(fileType);
			
			if (mType != null) {
				headers.setContentType(mType);
			} else {
				filePath = filePath.substring(filePath.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" + new String(filePath.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			throw e;
		}
		
		return entity;
	}
	
	// 기본 이미지
	private static ResponseEntity<byte[]> displayDefaultImg(String uploadPath, String filePath) throws Exception {
		InputStream in = new FileInputStream(uploadPath + filePath);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaUtils.getMediaType("JPG"));
		
		return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
	}
	
	// 파일 삭제 컨트롤러
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> deleteFile(String filePath, String distinction) throws Exception {
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
		
		if (distinction.equals("profile")) {
			if (MediaUtils.getMediaType(fileType) != null) {
				filePath = filePath.replace('/', File.separatorChar);
				
				new File(profileImgUploadPath + filePath).delete();
				new File(profileImgUploadPath + filePath.substring(0, 12) + filePath.substring(14)).delete();
			}
		} else {
			
		}
		
		return new ResponseEntity<>("파일이 삭제되었습니다.", HttpStatus.OK);
	}
	
}