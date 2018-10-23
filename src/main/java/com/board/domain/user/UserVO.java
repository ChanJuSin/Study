package com.board.domain.user;

import java.util.Date;

public class UserVO {

	private int idx;
	private String email;
	private String pw;
	private String name;
	private boolean auth_wt;
	private String session_key;
	private Date session_limit;
	private Date crea_time;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAuth_wt() {
		return auth_wt;
	}
	public void setAuth_wt(boolean auth_wt) {
		this.auth_wt = auth_wt;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public Date getSession_limit() {
		return session_limit;
	}
	public void setSession_limit(Date session_limit) {
		this.session_limit = session_limit;
	}
	public Date getCrea_time() {
		return crea_time;
	}
	public void setCrea_time(Date crea_time) {
		this.crea_time = crea_time;
	}
	
	@Override
	public String toString() {
		return "UserVO [idx=" + idx + ", email=" + email + ", pw=" + pw + ", name=" + name + ", auth_wt=" + auth_wt
				+ ", session_key=" + session_key + ", session_limit=" + session_limit + ", crea_time=" + crea_time
				+ "]";
	}

}
