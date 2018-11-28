package com.board.domain.board;

public class BoardVideoVO {

	private int board_idx;
	private String video_path;
	
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getVideo_path() {
		return video_path;
	}
	public void setVideo_path(String video_path) {
		this.video_path = video_path;
	}
	
	@Override
	public String toString() {
		return "BoardVideoVO [board_idx=" + board_idx + ", video_path=" + video_path + "]";
	}
	
}
