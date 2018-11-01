package com.board.util.file;

import java.io.File;

public class DeleteFile {

	public static void deleteFile(String folderPath, String filePath) {
		new File(folderPath + filePath).delete();
	}
	
}
