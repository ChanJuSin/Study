package com.board.domain.board;

public class BoardImageVO {

	private int idx;
	private int board_idex;
	private String writer;
	private String board_image_file_path;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoard_idex() {
		return board_idex;
	}
	public void setBoard_idex(int board_idex) {
		this.board_idex = board_idex;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getBoard_image_file_path() {
		return board_image_file_path;
	}
	public void setBoard_image_file_path(String board_image_file_path) {
		this.board_image_file_path = board_image_file_path;
	}
	
	@Override
	public String toString() {
		return "BoardImageVO [idx=" + idx + ", board_idex=" + board_idex + ", writer=" + writer
				+ ", board_image_file_path=" + board_image_file_path + "]";
	}
	
}
