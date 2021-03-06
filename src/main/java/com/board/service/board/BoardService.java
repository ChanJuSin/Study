package com.board.service.board;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.board.domain.board.BoardVO;

public interface BoardService {
	
	// 게시글 작성 
	public void write(BoardVO board, String[] board_original_image_paths, String[] board_thumbnail_image_paths, String[] video_paths, List<String> boardFilePathList) throws Exception;
	
	// 게시글리스트
	public List<BoardVO> list() throws Exception;
	
	// 게시글 상세페이지
	public Map<String, Object> read(BoardVO boardVO) throws Exception;
	
	// 게시글 삭제
	public void delete(int board_idx, int user_idx, String writer, String[] images, String[] files, String boardImgUploadPath, String boardFileUploadPath) throws Exception;
	
	// 게시글 수정시 파일 삭제
	public void modifyDeleteFile(int idx, String writer, String filePath) throws Exception;
	
	// 게시글 수정
	public void modify(int idx, int user_idx, String writer, String title, String content, MultipartFile[] files, String boardImageUploadPath, String boardFileUploadPath, String[] board_original_image_paths, String[] board_thumbnail_image_paths, String[] video_paths, String[] delete_video_paths) throws Exception;
	
	// 게시글 수정시 이미지 파일 삭제
	public void modifyDeleteImage(String board_idx, String user_idx, String[] originalImagePaths, String[] thumbnailImagePaths) throws Exception;
}
