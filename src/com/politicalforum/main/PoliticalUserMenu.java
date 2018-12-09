package com.politicalforum.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;

public class PoliticalUserMenu {
	private static Scanner sc = new Scanner(System.in);

	public static void menu(User user, PoliticalPartyServices politicalPartyServices) throws SQLException {
		int choice = 0;
		System.out.println("\t\tMenu\n\n1.Create Group \n\n2.Browse Groups\n\n3.My Groups");
		System.out.println("Enter Option:- ");
		choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			System.out.println("Enter Group Name:- ");
			String groupName = sc.nextLine();
			System.out.println("Describe this group:- ");
			String groupDescription = sc.nextLine();
			List<Group> similarGroups = politicalPartyServices.checkIfGroupExistsWithSimilarNames(groupName);
			if (similarGroups.isEmpty()) {
				System.out.println("Group Created ID:- "
						+ politicalPartyServices.createGroup(groupName, groupDescription, user).toString());
			} else {
				for (int i = 0; i < similarGroups.size(); i++) {
					System.out.println((i + 1) + ". " + similarGroups.get(i).getGroupName());
				}
			}

			break;
		case 2:
			CommonFeatures.joinGroup(user, politicalPartyServices);
			break;
		case 3:
			CommonFeatures.myGroups(user, politicalPartyServices);
			break;
		default:
			System.out.println("Wrong Option!");
			break;

		}
	}
}
