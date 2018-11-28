package com.board.persistence.board;

import static org.hamcrest.CoreMatchers.theInstance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.board.BoardFileVO;
import com.board.domain.board.BoardImageVO;
import com.board.domain.board.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.board.mapper.BoardMapper"; 

	@Override
	public void register(BoardVO board) throws Exception {
		sqlSession.insert(namespace + ".register", board);
	}

	@Override
	public void imageRegister(Map<String, Object> imageInfo) throws Exception {
		sqlSession.insert(namespace + ".imageRegister", imageInfo);
	}

	@Override
	public void fileRegister(Map<String, Object> fileInfo) throws Exception {
		sqlSession.insert(namespace + ".fileRegister", fileInfo);
	}

	@Override
	public List<BoardVO> getList() throws Exception {
		return sqlSession.selectList(namespace + ".getList");
	}

	@Override
	public BoardVO getPage(Map<String, Object> getPageInfo) throws Exception {
		return sqlSession.selectOne(namespace + ".getPage", getPageInfo);
	}

	@Override
	public List<BoardFileVO> getFile(Map<String, Object> getFileInfo) throws Exception {
		return sqlSession.selectList(namespace + ".getFile", getFileInfo);
	}

	@Override
	public List<BoardImageVO> getImages(Map<String, Object> getImagesInfo) throws Exception {
		return sqlSession.selectList(namespace + ".getImages", getImagesInfo);
	}

	@Override
	public void deleteBoard(Map<String, Object> deleteBoardInfo) throws Exception {
		sqlSession.delete(namespace + ".deleteBoard", deleteBoardInfo);
	}

	@Override
	public void deleteBoardFile(Map<String, Object> deleteBoardFileInfo) throws Exception {
		sqlSession.delete(namespace + ".deleteBoardFile", deleteBoardFileInfo);
	}

	@Override
	public void deleteBoardImage(Map<String, Object> deleteBoardImageInfo) throws Exception {
		sqlSession.delete(namespace + ".deleteBoardImage", deleteBoardImageInfo);
	}

	@Override
	public void modifyDeleteFile(Map<String, Object> deleteFileInfo) throws Exception {
		sqlSession.delete(namespace + ".modifyDeleteFile", deleteFileInfo);
	}

	@Override
	public void modifyDeleteImage(Map<String, Object> deleteImageInfo) throws Exception {
		sqlSession.delete(namespace + ".modifyDeleteImage", deleteImageInfo);
	}

	@Override
	public void modify(Map<String, Object> modifyInfo) throws Exception {
		sqlSession.update(namespace + ".modify", modifyInfo);
	}

	@Override
	public void imageWhetherChange(Map<String, Object> boardInfo) throws Exception {
		sqlSession.update(namespace + ".imageWhetherChange", boardInfo);
	}

	@Override
	public void fileWhetherChange(Map<String, Object> boardInfo) throws Exception {
		sqlSession.update(namespace + ".fileWhetherChange", boardInfo);
	}

	@Override
	public void videoRegister(Map<String, Object> videoMap) throws Exception {
		sqlSession.insert(namespace + ".videoRegister", videoMap);
	}

	@Override
	public void videoWhetherChange(Map<String, Object> videoMap) throws Exception {
		sqlSession.update(namespace + ".videoWhetherChange", videoMap);
	}

}
