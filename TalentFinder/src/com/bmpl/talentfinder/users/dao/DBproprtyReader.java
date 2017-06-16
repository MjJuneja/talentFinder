package com.bmpl.talentfinder.users.dao;

import java.util.ResourceBundle;

public interface DBproprtyReader {

	public static ResourceBundle rb = ResourceBundle.getBundle("dbconfig"); //resource bundle can read only properties file
	
	public static String getValue(String key){
		return rb.getString(key);
	}
}

