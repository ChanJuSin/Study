package com.board.domain.board;

import java.util.Date;

public class BoardVO {

	private int idx;
	private String writer;
	private String title;
	private String content;
	private int views;
	private Date crea_time;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	
	@Override
	public String toString() {
		return "Board [idx=" + idx + ", writer=" + writer + ", title=" + title + ", content=" + content + ", views="
				+ views + ", crea_time=" + crea_time + "]";
	}
	
}
