package com.board.domain.user;

public class UserVO {

	private String email;
	private String pw;
	private String name;
	private boolean auth_wt;
	
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
	
	@Override
	public String toString() {
		return "UserVO [email=" + email + ", pw=" + pw + ", name=" + name + ", auth_wt=" + auth_wt + "]";
	}
	
}
