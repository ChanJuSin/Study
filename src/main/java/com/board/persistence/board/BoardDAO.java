package com.board.persistence.board;

import java.util.List;
import java.util.Map;

import com.board.domain.board.BoardFileVO;
import com.board.domain.board.BoardImageVO;
import com.board.domain.board.BoardVO;
import com.board.domain.board.BoardVideoVO;

public interface BoardDAO {
	
	// 게시글 등록
	public void register(BoardVO board) throws Exception;
	
	// 이미지 등록
	public void imageRegister(Map<String, Object> imageInfo) throws Exception;
	
	// 이미지 등록 여부 true
	public void imageWhetherChange(Map<String, Object> boardInfo) throws Exception;
	
	// 파일 등록
	public void fileRegister(Map<String, Object> fileInfo) throws Exception;
	
	// 파일 등록 여부 true
	public void fileWhetherChange(Map<String, Object> boardInfo) throws Exception;
	
	// 게시글 리스트
	public List<BoardVO> getList() throws Exception;
	
	// 게시글 상세페이지 정보 얻음
	public BoardVO getPage(Map<String, Object> getPageInfo) throws Exception;
	
	// 게시글 파일 정보 얻음
	public List<BoardFileVO> getFile(Map<String, Object> getFileInfo) throws Exception;
	
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
	
	// 게시글 수정시 이미지 삭제
	public void modifyDeleteImage(Map<String, Object> deleteImageInfo) throws Exception;
	
	// 게시글 수정
	public void modify(Map<String, Object> modifyInfo) throws Exception;
	
	// 유튜브 영상 등록
	public void videoRegister(Map<String, Object> videoMap) throws Exception;
	
	// 유튜브 영상 삭제
	public void videoDelete(Map<String, Object> videoMap) throws Exception;
	
	// 영상 등록 여부 true
	public void videoWhetherChange(Map<String, Object> videoMap) throws Exception;
	
	// 영상 갯수 리턴
	public List<String> videoSelectPaths(Map<String, Object> videoMap) throws Exception;
	
	// 게시글 유튜브 영상 주소 리턴
	public List<BoardVideoVO> getVidoes(Map<String, Object> videoMap) throws Exception;
	
	// 마지막 insert idx 리턴
	public int getLastInsertId() throws Exception;
}
