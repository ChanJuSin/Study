package com.board.util.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	// 파일 업로드 후 업로드된 파일명 리턴
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		if (!new File(uploadPath).exists()) {
			new File(uploadPath).mkdirs();
		}
		
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		
		String fileType = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
	
		if (MediaUtils.getMediaType(fileType) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		
		return uploadedFileName;
	}
	
	// 년 월 일 폴더 생성
	private static void makeDir(String uploadPath, String... paths) {
		if (new File(uploadPath + paths[paths.length - 1]).exists()) {
			return;
		}
			
		for (String path: paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}
	
	// 년 월 일 생성 
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
			
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
			
		makeDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}
	
	// 썸네일 이미지 생성 후 년월일폴더 + 썸네일 이미지 파일명 리턴
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		
		File thumbnailFile = new File(thumbnailName);
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		ImageIO.write(destImg, fileType, thumbnailFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// 이미지 파일이 아닌경우 년월일폴더 + 파일명 리턴
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		String iconName = uploadPath + path + File.separator + fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
