package com.board.persistence.board;

import java.util.List;
import java.util.Map;

import com.board.domain.board.BoardFileVO;
import com.board.domain.board.BoardImageVO;
import com.board.domain.board.BoardProfileImageVO;
import com.board.domain.board.BoardVO;

public interface BoardDAO {
	
	// 게시글 등록
	public void register(BoardVO board) throws Exception;
	
	// 게시글 등록후 게시글 번호 얻음
	public int getPageIdx() throws Exception;
	
	// 이미지 등록
	public void imageRegister(Map<String, Object> imageInfo) throws Exception;
	
	// 파일 등록
	public void fileRegister(Map<String, Object> fileInfo) throws Exception;
	
	// 게시글 리스트
	public List<BoardVO> getList() throws Exception;
	
	// 게시글 상세페이지 정보 얻음
	public BoardVO getPage(Map<String, Object> getPageInfo) throws Exception;
	
	// 게시글 파일 정보 얻음
	public List<BoardFileVO> getFile(Map<String, Object> getFileInfo) throws Exception;
	
	// 게시글 작성자 프로필 이미지 정보 얻음
	public BoardProfileImageVO getProfileImage(Map<String, Object> getImageInfo) throws Exception;
	
	// 게시글 이미지 리스트 얻음
	public List<BoardImageVO> getImages(Map<String, Object> getImagesInfo) throws Exception;
	
	// 게시글 삭제
	public void deleteBoard(Map<String, Object> deleteBoardInfo) throws Exception;
	
	// 게시글 파일 삭제
	public void deleteBoardFile(Map<String, Object> deleteBoardFileInfo) throws Exception;
	
	// 게시글 이미지 삭제
	public void deleteBoardImage(Map<String, Object> deleteBoardImageInfo) throws Exception;
	
	// 게시글 수정시 파일 삭제
	public void modifyDeleteFile(Map<String, Object> deleteFileInfo) throws Exception;
	
}
