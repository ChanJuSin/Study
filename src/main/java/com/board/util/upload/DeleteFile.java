package com.board.util.upload;

import java.io.File;

public class DeleteFile {

	public static void deleteFile(String folderPath, String filePath) {
		System.out.println(folderPath + filePath);
		new File(folderPath + filePath).delete();
	}
	
}
