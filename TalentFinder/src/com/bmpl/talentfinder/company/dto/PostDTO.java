package com.bmpl.talentfinder.company.dto;

import java.time.LocalTime;

public class PostDTO {
   private String post;
   private LocalTime time;
   private String companyName;
   private String star;
   private String email;
   public PostDTO(){
	   
   }
public PostDTO(String companyName, String post){
	this.companyName=companyName;
	this.post = post;
//	this.time=(z time2;
}
public String getPost() {
	return post;
}
public void setPost(String post) {
	this.post = post;
}
public LocalTime getTime() {
	return time;
}
public void setTime(LocalTime time) {
	this.time = time;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getStar() {
	return star;
}
public void setStar(String star) {
	this.star = star;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
   
   
}
