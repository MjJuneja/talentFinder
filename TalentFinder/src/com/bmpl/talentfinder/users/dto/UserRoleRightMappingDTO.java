package com.bmpl.talentfinder.users.dto;

import java.util.ArrayList;

public class UserRoleRightMappingDTO {
	private String userid;
	private String role;
	private ArrayList<RightDTO> rightlist = new ArrayList<>(); //java 7 makes this feature to keep right angular brackets open
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public ArrayList<RightDTO> getRightlist() {
		return rightlist;
	}
	public void setRightlist(ArrayList<RightDTO> rightlist) {
		this.rightlist = rightlist;
	}
	
	
}
