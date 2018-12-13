package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.beans.User;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.Helper;

public class Login {

	private static Scanner sc = new Scanner(System.in);

	public static void userLogin(PoliticalPartyServices politicalPartyServices) {
		try {
			System.out.println("Enter Email-Id:- ");
			String emailId = sc.nextLine();
			System.out.println("Enter Password:- ");
			String password = sc.nextLine();
			User user = politicalPartyServices.login(emailId, password);
			System.out.println("Login success  ");
			if (Helper.checkIfUserIsPolitician(user.getUserId())) {
				PoliticalUserMenu.menu(user, politicalPartyServices);
			} else {
				GeneralUserMenu.menu(user, politicalPartyServices);
			}
		} catch (InvalidCredentialsException e) {
			System.err.println(e.getMessage());
		}
	}

}
