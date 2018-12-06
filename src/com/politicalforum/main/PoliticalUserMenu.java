package com.politicalforum.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.services.PoliticalPartyServices;

public class PoliticalUserMenu {
	private static Scanner sc = new Scanner(System.in);

	public static void menu(PoliticalUser politicalUser, PoliticalPartyServices politicalPartyServices)
			throws SQLException {
		int choice = 0;
		System.out.println("\t\tMenu\n\n1.Create Group \n\n2.Browse Groups\n");
		System.out.println("Enter Option:- ");
		choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter Group Name:- ");
			String groupName = sc.next();
			System.out.println("Describe this group:- ");
			String groupDescription = sc.next();
			List<Group> similarGroups = politicalPartyServices.checkIfGroupExistsWithSimilarNames(groupName);
			if (similarGroups.isEmpty()) {
				System.out.println("Group Created ID:- "
						+ politicalPartyServices.createGroup(groupName, groupDescription, politicalUser).toString());
			} else {
				for (int i = 0; i < similarGroups.size(); i++) {
					System.out.println((i + 1) + ". " + similarGroups.get(i).getGroupName());
				}
			}

			break;
		case 2:
			System.out.println("Available groups to join");
			List<Group> groups = politicalPartyServices.browseGroups();
			HashMap<Integer, Group> map = new HashMap<>();
			for (int i = 0; i < groups.size(); i++) {
				map.put((i + 1), groups.get(i));
				System.out.println((i + 1) + ". " + groups.get(i).getGroupName());
			}
			System.out.println("Enter the group number to join:- ");
			int groupNumber = sc.nextInt();
			if (map.containsKey(groupNumber)) {
				System.out.println("Logic to join the group:- " + map.get(groupNumber).toString());
			}
			break;
		default:
			System.out.println("Wrong Option!");
			break;

		}
	}
}
