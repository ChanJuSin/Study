package com.board.domain.board;

public class BoardImageVO {

	private int board_idx;
	private String board_original_image_path;
	private String board_thumbnail_image_path;
	
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getBoard_original_image_path() {
		return board_original_image_path;
	}
	public void setBoard_original_image_path(String board_original_image_path) {
		this.board_original_image_path = board_original_image_path;
	}
	public String getBoard_thumbnail_image_path() {
		return board_thumbnail_image_path;
	}
	public void setBoard_thumbnail_image_path(String board_thumbnail_image_path) {
		this.board_thumbnail_image_path = board_thumbnail_image_path;
	}
	
	@Override
	public String toString() {
		return "BoardImageVO [board_idx=" + board_idx + ", board_original_image_path=" + board_original_image_path
				+ ", board_thumbnail_image_path=" + board_thumbnail_image_path + "]";
	}
	
}
