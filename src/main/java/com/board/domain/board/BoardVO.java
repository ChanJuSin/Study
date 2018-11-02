package com.board.domain.board;

import java.util.Date;

public class BoardVO {

	private int idx;
	private int user_idx;
	private String writer;
	private String title;
	private String content;
	private int views;
	private Date crea_time;
	private String thumbnail_image_path; // 프로필 이미지 경로
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Date getCrea_time() {
		return crea_time;
	}
	public void setCrea_time(Date crea_time) {
		this.crea_time = crea_time;
	}
	public String getThumbnail_image_path() {
		return thumbnail_image_path;
	}
	public void setThumbnail_image_path(String thumbnail_image_path) {
		this.thumbnail_image_path = thumbnail_image_path;
	}
	
	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", user_idx=" + user_idx + ", writer=" + writer + ", title=" + title
				+ ", content=" + content + ", views=" + views + ", crea_time=" + crea_time + ", thumbnail_image_path="
				+ thumbnail_image_path + "]";
	}
	
}
