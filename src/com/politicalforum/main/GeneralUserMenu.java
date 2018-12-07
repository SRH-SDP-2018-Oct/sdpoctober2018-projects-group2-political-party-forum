package com.politicalforum.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;

public class GeneralUserMenu {

	private static Scanner sc = new Scanner(System.in);

	public static void menu(User user, PoliticalPartyServices politicalPartyServices) throws SQLException {
		int choice = 0;
		System.out.println("\t\tMenu\n\n1.Browse Groups \n");
		System.out.println("Enter Option:- ");
		choice = sc.nextInt();
		switch (choice) {
		case 1:
			user = CommonFeatures.joinGroup(user, politicalPartyServices);
			break;
		case 2:
			
			break;
		default:
			System.out.println("Wrong Option!");
			break;

		}
	}
}
