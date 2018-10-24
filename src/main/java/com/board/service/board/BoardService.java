package com.board.service.board;

import java.util.List;
import java.util.Map;

import com.board.domain.board.BoardVO;

public interface BoardService {
	
	// 게시글 작성 
	public void write(BoardVO board, String[] images, List<String> boardFilePathList) throws Exception;
	
	// 게시글리스트
	public List<BoardVO> list() throws Exception;
	
	// 게시글 상세페이지
	public Map<String, Object> read(int idx, int user_idx, String writer) throws Exception;
	
	// 게시글 삭제
	public void delete(int board_idx, int user_idx, String writer, String[] images, String[] files, String boardImgUploadPath, String boardFileUploadPath) throws Exception;
	
	// 게시글 수정시 파일 삭제
	public void modifyDeleteFile(int idx, String writer, String filePath) throws Exception;
	
}
