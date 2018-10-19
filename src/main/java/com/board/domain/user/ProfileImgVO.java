package com.board.domain.user;

import java.util.Date;

public class ProfileImgVO {

	private String email;
	private String thumbnail_file_path;
	private Date crea_time;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getThumbnail_file_path() {
		return thumbnail_file_path;
	}
	public void setThumbnail_file_path(String thumbnail_file_path) {
		this.thumbnail_file_path = thumbnail_file_path;
	}
	public Date getCrea_time() {
		return crea_time;
	}
	public void setCrea_time(Date crea_time) {
		this.crea_time = crea_time;
	}
	
	@Override
	public String toString() {
		return "ProfileImg [email=" + email + ", thumbnail_file_path=" + thumbnail_file_path + ", crea_time="
				+ crea_time + "]";
	}
	
}
