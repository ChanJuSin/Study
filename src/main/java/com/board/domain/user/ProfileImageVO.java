package com.board.domain.user;

import java.util.Date;

public class ProfileImageVO {

	private int user_idx;
	private String original_image_path;
	private String thumbnail_image_path;
	private Date register_image_time;
	
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getOriginal_image_path() {
		return original_image_path;
	}
	public void setOriginal_image_path(String original_image_path) {
		this.original_image_path = original_image_path;
	}
	public String getThumbnail_image_path() {
		return thumbnail_image_path;
	}
	public void setThumbnail_image_path(String thumbnail_image_path) {
		this.thumbnail_image_path = thumbnail_image_path;
	}
	public Date getRegister_image_time() {
		return register_image_time;
	}
	public void setRegister_image_time(Date register_image_time) {
		this.register_image_time = register_image_time;
	}
	
	@Override
	public String toString() {
		return "ProfileImageVO [user_idx=" + user_idx + ", original_image_path=" + original_image_path
				+ ", thumbnail_image_path=" + thumbnail_image_path + ", register_image_time=" + register_image_time
				+ "]";
	}
	
}
