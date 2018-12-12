package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.services.PoliticalPartyServices;

public class GeneralUserMenu {

	private static Scanner sc = new Scanner(System.in);

	public static void menu(User user, PoliticalPartyServices politicalPartyServices) {
		int choice = 0;
		do {
			try {
			System.out.println("\t\tMenu\n\n1. Browse Groups \n2. My Groups \n3. Notifications\n4. Logout\n");
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
				CommonFeatures.notificationView(user, politicalPartyServices);
				break;
			case 4:
				break;
			default:
				System.out.println("Wrong Option!");
				break;
			}
			}catch(GroupAlreadyJoinedException e) {
				System.out.println(e.getMessage());
			}
		} while (choice != 4);
	}
}
