package com.board.domain.board;

public class BoardFileVO {

	private int board_idx;
	private String board_file_path;
	
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getBoard_file_path() {
		return board_file_path;
	}
	public void setBoard_file_path(String board_file_path) {
		this.board_file_path = board_file_path;
	}
	
	@Override
	public String toString() {
		return "BoardFileVO [board_idx=" + board_idx + ", board_file_path=" + board_file_path + "]";
	}

}
