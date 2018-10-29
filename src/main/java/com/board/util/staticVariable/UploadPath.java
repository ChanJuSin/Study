package com.board.util.staticVariable;

public class UploadPath {

	public static final String PROFILE_IMAGE_UPLOAD_PATH = "C:\\dev\\upload\\profileImage";
	public static final String BOARD_IMAGE_UPLOAD_PATH = "C:\\dev\\upload/boardImage";
	public static final String BOARD_FILE_UPLOAD_PATH = "C:\\dev\\upload\\board";
	
	public static void main(String[] args) {
		System.out.println(UploadPath.PROFILE_IMAGE_UPLOAD_PATH);
		System.out.println(UploadPath.BOARD_IMAGE_UPLOAD_PATH);
		System.out.println(UploadPath.BOARD_FILE_UPLOAD_PATH);
	}
	
}
