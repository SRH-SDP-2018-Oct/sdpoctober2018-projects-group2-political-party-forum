package com.politicalforum.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mindrot.jbcrypt.BCrypt;

import com.politicalforum.exceptions.UnknownDateFormatException;

public class Helper {

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD/MM/YYYY");
	
	public static Boolean checkIfUserIsPolitician(String id) {
		return id.indexOf('P') > -1 ? true : false;
	}
	
	public static Date getCurrentDateOfTypeJavaSql() {
		java.util.Date currentDate = new java.util.Date();
		return new Date(currentDate.getTime());
	}

	public static Date convertDateToSqlDate(String date) throws UnknownDateFormatException {
		try {
			return new Date(DATE_FORMAT.parse(date).getTime());
		} catch (ParseException e) {
			throw new UnknownDateFormatException("Please enter the correct date format - DD/MM/YYYY");
		}
		//return null;
	}
	
	public static String generateHashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public static Boolean isPasswordCorrect(String password, String encryptedPassword) {
		return BCrypt.checkpw(password, encryptedPassword);
	}
		
}
