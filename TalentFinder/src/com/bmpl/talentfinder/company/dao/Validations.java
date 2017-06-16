package com.bmpl.talentfinder.company.dao;



public class Validations {
	private int phoneLength =8;
	
	private boolean isBlank(String data){
		boolean isBlank=false;
		if(data.length()>0){
			isBlank=true;
		}
		return isBlank;
	}
	public boolean isValidUserid(String userid){
		boolean isValid = false;
		if(isBlank(userid)){
		if(userid.contains("@") && userid.contains(".com")){
			isValid = true;
		}
		}
		return isValid;
	}
	
	public boolean isValidPhone(String phone){
		boolean isValid = false;
			if(phone.length()==phoneLength){
				isValid=true;
			}
			return isValid;
		
	}
	
	
}
