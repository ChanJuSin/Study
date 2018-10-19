package com.board.persistence.board;

import java.util.List;
import java.util.Map;

import com.board.domain.board.BoardFileVO;
import com.board.domain.board.BoardVO;

public interface BoardDAO {
	
	// 게시글 등록
	public void register(BoardVO board) throws Exception;
	
	// 이미지 등록
	public void imageRegister(Map<String, Object> imageInfo) throws Exception;
	
	// 파일 등록
	public void fileRegister(Map<String, Object> fileInfo) throws Exception;
	
	// 게시글 리스트
	public List<BoardVO> getList() throws Exception;
	
	// 게시글 상세페이지 정보 얻음
	public BoardVO getPage(Map<String, Object> getPageInfo) throws Exception;
	
	// 게시글 이미지 정보 얻음
	public List<BoardFileVO> getFile(Map<String, Object> getFileInfo) throws Exception;
	
}
