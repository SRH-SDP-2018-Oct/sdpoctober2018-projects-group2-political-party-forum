package com.politicalforum.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";

	public static final Pattern VALID_USER_NAME = Pattern.compile("^[A-Za-z, ]{1,10}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_AGE_LIMIT = Pattern.compile("^[0-9]{2}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_AADHAR_LENGTH = Pattern.compile("^[0-9]{12}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_POLITICALID_LENGTH = Pattern.compile("^[0-9]{6}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_GENDER = Pattern.compile("^(?:m|M|male|Male|f|F|female|Female|o|O|others|Other)$",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_USER_REGION = Pattern.compile("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,10}$",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PASSWORD = Pattern
			.compile("^.*(?=.{4,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$)");

	public static boolean validateName(String name) {
		Matcher matcher = VALID_USER_NAME.matcher(name);
		boolean flag = matcher.find();
		if (!flag) {
			System.out.println("\nPlease Enter Name \n");
		}
		return flag;
	}

	public static boolean validateEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS.matcher(email);
		boolean flag = matcher.find();
		if (!flag) {
			System.out.println(ANSI_RED + "\nPlease Enter Valid EmailID \n" + ANSI_RED);
		}
		return flag;
	}

	public static boolean validateAge(String age) {
		Matcher matcher = VALID_AGE_LIMIT.matcher(String.valueOf(age));
		boolean flag = matcher.find();
		if (!flag) {
			System.out.println(ANSI_RED + "\n Please Enter Valid Age \n" + ANSI_RESET);
			System.out.println("\033[0m BLABLA \033[0m\n");
		}
		return flag;
	}

	public static boolean validateAadhar(CharSequence aadhar) {
		Matcher matcher = VALID_AADHAR_LENGTH.matcher(aadhar);
		boolean flag = matcher.find();
		if (!flag) {
			System.out.println(ANSI_RED + "\nPlease Enter Valid 12 Digit Aadhar Number \n" + ANSI_RED);
		}
		return flag;
	}

	public static boolean validatePoliticalID(CharSequence politicalId) {
		Matcher matcher = VALID_POLITICALID_LENGTH.matcher(politicalId);
		boolean flag = matcher.find();
		if (!flag) {
			System.out.println(ANSI_RED + "\n Please Enter Valid 6 Digit Political Number \n" + ANSI_RED);
		}
		return flag;
	}

	public static boolean validateGender(CharSequence gender) {
		Matcher matcher = VALID_GENDER.matcher(gender);
		boolean flag = matcher.find();
		if (!flag) {
			System.out.println("Please Enter Correct Gender");
		}
		return flag;
	}

	public static boolean validateRegion(String region) {
		Matcher matcher = VALID_USER_REGION.matcher(region);
		boolean flag = matcher.find();
		if (!flag) {
			System.out.println("\nPlease Enter Correct Region \n");
		}
		return flag;
	}

	public static boolean validatePassword(String password) {
		Matcher matcher = VALID_PASSWORD.matcher(password);
		boolean flag = matcher.find();
		if (!flag) {
			System.out.println("\n Please Enter 4 Didit Password");
		}
		return flag;
	}

}
