package com.board.domain.board;

import java.util.Date;

public class BoardVO {

	private int idx;
	private int user_idx;
	private String writer;
	private String title;
	private String content;
	private int views;
	private boolean image_whether;
	private boolean file_whether;
	private boolean video_whether;
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
	public boolean isImage_whether() {
		return image_whether;
	}
	public void setImage_whether(boolean image_whether) {
		this.image_whether = image_whether;
	}
	public boolean isFile_whether() {
		return file_whether;
	}
	public void setFile_whether(boolean file_whether) {
		this.file_whether = file_whether;
	}
	public boolean isVideo_whether() {
		return video_whether;
	}
	public void setVideo_whether(boolean video_whether) {
		this.video_whether = video_whether;
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
				+ ", content=" + content + ", views=" + views + ", image_whether=" + image_whether + ", file_whether="
				+ file_whether + ", video_whether=" + video_whether + ", crea_time=" + crea_time
				+ ", thumbnail_image_path=" + thumbnail_image_path + "]";
	}
	
}
