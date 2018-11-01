package com.board.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DisplayImage {

	// 이미지 리턴
	public static ResponseEntity<byte[]> displayImage(String imagePath, String folderPath) throws Exception {
		ResponseEntity < byte[] > entity = null;

		HttpHeaders headers = new HttpHeaders();

		imagePath = imagePath.replace('/', File.separatorChar);

		String imageType = imagePath.substring(imagePath.lastIndexOf(".") + 1);

		headers.setContentType(MediaUtils.getMediaType(imageType));

		try (InputStream in = new FileInputStream(folderPath + imagePath)) {
		    entity = new ResponseEntity < byte[] > (IOUtils.toByteArray( in ), headers, HttpStatus.CREATED);
		} catch (Exception e) {
		    throw e;
		}		
		
		return entity;
	}
	
}
