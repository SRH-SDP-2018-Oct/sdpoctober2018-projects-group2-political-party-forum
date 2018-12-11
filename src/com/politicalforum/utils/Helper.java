package com.politicalforum.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mindrot.jbcrypt.BCrypt;

public class Helper {

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD/MM/YYYY");
	
	public static Boolean checkIfUserIsPolitician(String id) {
		return id.indexOf('P') > -1 ? true : false;
	}
	
	public static Date getCurrentDateOfTypeJavaSql() {
		java.util.Date currentDate = new java.util.Date();
		return new Date(currentDate.getTime());
	}

	public static Date convertDateToSqlDate(String date) {
		try {
			return new Date(DATE_FORMAT.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String generateHashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public static Boolean isPasswordCorrect(String password, String encryptedPassword) {
		return BCrypt.checkpw(password, encryptedPassword);
	}
		
}
