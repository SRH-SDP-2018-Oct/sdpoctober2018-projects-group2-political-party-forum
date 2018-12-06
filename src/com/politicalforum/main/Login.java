package com.politicalforum.main;

import java.util.HashMap;
import java.util.Scanner;

import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.Helper;

public class Login {

	private static Scanner sc = new Scanner(System.in);

	public static void userLogin(PoliticalPartyServices politicalPartyServices) {
		try {
			System.out.println("Enter Email-Id:- ");
			String emailId = sc.next();
			System.out.println("Enter Password:- ");
			String password = sc.next();
			HashMap<String, Object> user = politicalPartyServices.login(emailId, password);
			System.out.println("Login success  " + user);
			for (String key : user.keySet()) {
				if (Helper.checkIfUserIsPolitician(key)) {
					PoliticalUserMenu.menu((PoliticalUser) user.get(key), politicalPartyServices);
				} else {
					GeneralUserMenu.menu((User) user.get(key), politicalPartyServices);
					System.out.println("General User Login");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
