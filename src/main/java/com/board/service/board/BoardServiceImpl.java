package com.board.service.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.board.BoardVO;
import com.board.persistence.board.BoardDAO;
import com.board.util.upload.DeleteFile;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void write(BoardVO boardVO, String[] images, List<String> boardFilePathList) throws Exception {
		boardDAO.register(boardVO);
		
		Map<String, Object> fileMap = new HashMap<>();
		fileMap.put("writer", boardVO.getWriter());
		fileMap.put("board_idx", boardDAO.getPageIdx());
		fileMap.put("user_idx", boardVO.getIdx());
		
		if (images != null) {
			for (String image : images) {
				fileMap.put("boardImageFilePath", image);
				boardDAO.imageRegister(fileMap);
			}
		}
		
		if (boardFilePathList != null) {
			for (String boardFilePath : boardFilePathList) {
				fileMap.put("boardFilePath", boardFilePath);
				boardDAO.fileRegister(fileMap);
			}
		}
	}

	@Override
	public List<BoardVO> list() throws Exception {
		return boardDAO.getList();
	}

	@Transactional
	@Override
	public Map<String, Object> read(int idx, int user_idx, String writer) throws Exception {
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("idx", idx);
		infoMap.put("user_idx", user_idx);
		infoMap.put("writer", writer);
		
		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("boardVO", boardDAO.getPage(infoMap));
		pageInfo.put("boardFileVO", boardDAO.getFile(infoMap));
		pageInfo.put("boardProfileImageVO", boardDAO.getProfileImage(infoMap));
		pageInfo.put("boardImageVO", boardDAO.getImages(infoMap));
		
		return pageInfo;
	}

	@Transactional
	@Override
	public void delete(int board_idx, int user_idx, String writer, String[] images, String[] files, String boardImgUploadPath, String boardFileUploadPath) throws Exception {
		Map<String, Object> deleteInfoMap = new HashMap<>();
		deleteInfoMap.put("board_idx", board_idx);
		deleteInfoMap.put("user_idx", user_idx);
		deleteInfoMap.put("writer", writer);
		
		boardDAO.deleteBoard(deleteInfoMap);
		
		if (images.length != 0) {
			for (String image: images) {
				DeleteFile.deleteFile(boardImgUploadPath, image);
				DeleteFile.deleteFile(boardImgUploadPath, image.substring(0, 12) + "s_" + image.substring(12));
			}
			boardDAO.deleteBoardFile(deleteInfoMap);
		} 
		
		if (files.length != 0) {
			for (String file: files) {
				DeleteFile.deleteFile(boardFileUploadPath, file);
			}
			boardDAO.deleteBoardImage(deleteInfoMap);
		}
	}

	@Override
	public void modifyDeleteFile(int idx, String writer, String filePath) throws Exception {
		Map<String, Object> deleteFileInfo = new HashMap<>();
		deleteFileInfo.put("board_idx", idx);
		deleteFileInfo.put("writer", writer);
		deleteFileInfo.put("filePath", filePath);
		
		boardDAO.modifyDeleteFile(deleteFileInfo);
	}

}
