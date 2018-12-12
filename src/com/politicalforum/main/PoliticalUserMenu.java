package com.politicalforum.main;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyExistException;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.exceptions.UserAlreadyExistsException;
import com.politicalforum.services.PoliticalPartyServices;

public class PoliticalUserMenu {
	private static Scanner sc = new Scanner(System.in);

	public static void menu(User user, PoliticalPartyServices politicalPartyServices)
			throws UserAlreadyExistsException, GroupAlreadyExistException {
		int choice = 0;
		do {
			System.out.println("\t\tMenu\n\n1. Create Group \n2. Browse Groups\n3. My Groups\n4. Notifications\n5. Logout\n");
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
					HashMap<Integer, Group> map = new HashMap<>();
					System.out.println("\nSimilar Groups Available to join:-\n");
					for (int i = 0; i < similarGroups.size(); i++) {
						System.out.println((i + 1) + ". " + similarGroups.get(i).getGroupName());
						map.put(i + 1, similarGroups.get(i));
					}
					System.out.println("Enter the group number to join:- ");
					int groupNumber = sc.nextInt();
					sc.nextLine();
					try {
						user = politicalPartyServices.joinGroup(user, similarGroups.get(groupNumber));
					} catch (GroupAlreadyJoinedException e) {
						e.printStackTrace();
					}
					user.setSelectedGroup(user.getGroups().get(user.getGroups().size() - 1));
					System.out.println(user.getSelectedGroup().getGroupName() + " Group joined");
					GroupFeatures.viewGroup(user, politicalPartyServices);
				}

				break;
			case 2:
				try {
					CommonFeatures.joinGroup(user, politicalPartyServices);
				} catch (GroupAlreadyJoinedException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					CommonFeatures.myGroups(user, politicalPartyServices);
				} catch (GroupAlreadyJoinedException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				CommonFeatures.notificationView(user, politicalPartyServices);
				break;
			case 5:
				break;
			default:
				System.out.println("Wrong Option!");
				break;

			}
		} while (choice != 5);
	}
}
