package com.board.service.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.board.BoardVO;
import com.board.persistence.board.BoardDAO;
import com.board.util.file.FileRelatedUtils;
import com.board.util.file.UploadFileUtils;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void write(BoardVO boardVO, String[] board_original_image_paths, String[] board_thumbnail_image_paths, List<String> boardFilePathList) throws Exception {
		boardDAO.register(boardVO);
		
		Map<String, Object> fileMap = new HashMap<>();
		/*fileMap.put("board_idx", boardDAO.getPageIdx());*/
		fileMap.put("user_idx", boardVO.getUser_idx());
		
		if ((board_original_image_paths != null) && (board_thumbnail_image_paths != null)) {
			for (int index = 0; index < board_original_image_paths.length; index++) {
				fileMap.put("boardOriginalImagePath", board_original_image_paths[index]);
				fileMap.put("boardThumbnailImagePath", board_thumbnail_image_paths[index]);
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
	public Map<String, Object> read(BoardVO boardVO) throws Exception {
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("idx", boardVO.getIdx());
		infoMap.put("user_idx", boardVO.getUser_idx());
		
		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("boardVO", boardDAO.getPage(infoMap));
		pageInfo.put("boardFileVO", boardDAO.getFile(infoMap));
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
		
		if (images.length > 0) {
			for (String image: images) {
				FileRelatedUtils.deleteFile(boardImgUploadPath, image);
				FileRelatedUtils.deleteFile(boardImgUploadPath, image.substring(0, 12) + "s_" + image.substring(12));
			}
			boardDAO.deleteBoardFile(deleteInfoMap);
		} 
		
		if (files.length > 0) {
			for (String file: files) {
				FileRelatedUtils.deleteFile(boardFileUploadPath, file);
			}
			boardDAO.deleteBoardImage(deleteInfoMap);
		}
	}

	@Override
	public void modifyDeleteFile(int idx, String writer, String filePath) throws Exception {
		Map<String, Object> deleteFileInfo = new HashMap<>();
		deleteFileInfo.put("board_idx", idx);
		deleteFileInfo.put("writer", writer);
		deleteFileInfo.put("boardFilePath", filePath);
		
		boardDAO.modifyDeleteFile(deleteFileInfo);
	}

	@Override
	public void modify(int idx, int user_idx, String writer, String title, String content, MultipartFile[] files,
			String[] addImages, String[] deleteImages, String boardImageUploadPath, String boardFileUploadPath) throws Exception {
		Map<String, Object> modifyInfo = new HashMap<>();
		modifyInfo.put("board_idx", idx);
		modifyInfo.put("user_idx", user_idx);
		modifyInfo.put("writer", writer);
		modifyInfo.put("title", title);
		modifyInfo.put("content", content);

		if (deleteImages.length > 0) {
			for (String deleteImage: deleteImages) {
				String filePath = deleteImage.split("\\.")[0] + "." + deleteImage.split("\\.")[1].substring(0, 3);
				FileRelatedUtils.deleteFile(boardImageUploadPath, filePath);
				FileRelatedUtils.deleteFile(boardImageUploadPath, filePath.substring(0, 12) + "s_" + filePath.substring(12));
				modifyInfo.put("boardImageFilePath", filePath);
				boardDAO.modifyDeleteImage(modifyInfo);
			}
		}
		
		if (addImages.length > 0) {
			for (String addImage: addImages) {
				String filePath = addImage.split("\\.")[0] + "." + addImage.split("\\.")[1].substring(0, 3);
				modifyInfo.put("boardImageFilePath", filePath);
				boardDAO.imageRegister(modifyInfo);
			}
		}
		
		if (files.length > 0) {
			for (MultipartFile file: files) {
				if (file.getSize() > 0) {
					String filePath = UploadFileUtils.uploadFile(boardFileUploadPath, file.getOriginalFilename(), file.getBytes());
					modifyInfo.put("boardFilePath", filePath);
					boardDAO.fileRegister(modifyInfo);
				}
			}
		}
		
		boardDAO.modify(modifyInfo);
	}

}
