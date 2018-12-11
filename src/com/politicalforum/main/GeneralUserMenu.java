package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.exceptions.UserAlreadyExistsException;
import com.politicalforum.services.PoliticalPartyServices;

public class GeneralUserMenu {

	private static Scanner sc = new Scanner(System.in);

	public static void menu(User user, PoliticalPartyServices politicalPartyServices)
			throws UserAlreadyExistsException, GroupAlreadyJoinedException {
		int choice = 0;
		do {
			System.out.println("\t\tMenu\n\n1. Browse Groups \n2. My Groups");
			System.out.println("Enter Option:- ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				CommonFeatures.joinGroup(user, politicalPartyServices);
				break;
			case 2:
				CommonFeatures.myGroups(user, politicalPartyServices);
				break;
			case 3:
				break;
			default:
				System.out.println("Wrong Option!");
				break;
			}
		} while (choice != 3);
	}
}
