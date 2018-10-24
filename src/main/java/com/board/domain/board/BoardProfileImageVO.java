package com.board.domain.board;

public class BoardProfileImageVO {

	private int idx;
	private int board_idx;
	private String writer;
	private String thumbnail_file_path;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getThumbnail_file_path() {
		return thumbnail_file_path;
	}
	public void setThumbnail_file_path(String thumbnail_file_path) {
		this.thumbnail_file_path = thumbnail_file_path;
	}
	
	@Override
	public String toString() {
		return "BoardImageVO [idx=" + idx + ", board_idx=" + board_idx + ", writer=" + writer + ", thumbnail_file_path="
				+ thumbnail_file_path + "]";
	}
	
}
