package com.board.service.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.board.BoardVO;
import com.board.persistence.board.BoardDAO;

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

	@Override
	public Map<String, Object> read(int idx, String writer) throws Exception {
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("idx", idx);
		infoMap.put("writer", writer);
		
		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("boardVO", boardDAO.getPage(infoMap));
		pageInfo.put("fileInfo", boardDAO.getFile(infoMap));
		
		return pageInfo;
	}

}
