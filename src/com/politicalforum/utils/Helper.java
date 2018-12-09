package com.politicalforum.utils;

import java.sql.Date;

import org.mindrot.jbcrypt.BCrypt;

public class Helper {

	public static Boolean checkIfUserIsPolitician(String id) {
		return id.indexOf('P') > -1 ? true : false;
	}
	
	public static Date getCurrentDateOfTypeJavaSql() {
		java.util.Date currentDate = new java.util.Date();
		return new Date(currentDate.getTime());
	}

	public static Date convertDateToSqlDate(java.util.Date date) {
		return new Date(date.getTime());
	}
	
	public static String generateHashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public static Boolean isPasswordCorrect(String password, String encryptedPassword) {
		return BCrypt.checkpw(password, encryptedPassword);
	}
		
}
