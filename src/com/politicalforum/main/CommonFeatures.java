package com.politicalforum.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.GroupDiscussion;
import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.Helper;

public class CommonFeatures {

	private static Scanner sc = new Scanner(System.in);

	public static User joinGroup(User user, PoliticalPartyServices politicalPartyServices) throws SQLException {
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
			user = politicalPartyServices.joinGroup(user, map.get(groupNumber));
			user.setSelectedGroup(user.getGroups().get(user.getGroups().size() - 1));
			System.out.println(user.getSelectedGroup().getGroupName() + " Group joined");
			viewGroup(user, politicalPartyServices);
		}

		return user;
	}

	public static void myGroups(User user, PoliticalPartyServices politicalPartyServices) {
		int choice = 0;
		HashMap<Integer, Group> map = new HashMap<>();

		System.out.println("\n********MY GROUPS*************");

		for (int i = 0; i < user.getGroups().size(); i++) {
			System.out.println((i + 1) + ". " + user.getGroups().get(i).getGroupName());
			map.put((i + 1), user.getGroups().get(i));
		}
		System.out.println("Your Choice:- ");
		choice = sc.nextInt();
		user.setSelectedGroup(map.get(choice));
		System.out.println("User selected group:- "+ user.getSelectedGroup().toString());
		viewGroup(user, politicalPartyServices);

	}

	public static void viewGroup(User user, PoliticalPartyServices politicalPartyServices) {
		int choice = 0;
		do {
			System.out.println("**********Group Menu************");
			System.out.println("\n" + user.getSelectedGroup().getGroupName());
			System.out.println("\nAbout This Group:- " + user.getSelectedGroup().getGroupDescription());
			System.out.println(
					"\n\n1. View Discussions\n2. View Polls\n3. View Projects for this group\n4. Create Discussions\n5. Create Polls");
			boolean isUserPolitician = Helper.checkIfUserIsPolitician(user.getUserId());
			if (isUserPolitician) {
				System.out.println("6. Create Project\n7. View Dashboard");
			}
			System.out.println("\n\n Press 0 to go to home page.");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				List<GroupDiscussion> discussions = politicalPartyServices
						.viewAllDiscussions(user.getSelectedGroup().getGroupId());
				System.out.println("All Discussions:- ");
				for (int i = 0; i < discussions.size(); i++) {
					System.out.println((i + 1) + ". " + discussions.get(i).getGroupDiscussionName());
				}
				break;
			case 2:
			case 3:
			case 4:
				System.out.println("Enter Discussion Name:- ");
				String groupDiscussionName = sc.nextLine();
				System.out.println("Enter Discusson Body:- ");
				String groupDiscussionBody = sc.nextLine();
				politicalPartyServices.createDiscussion(user, groupDiscussionName, groupDiscussionBody);
				System.out.println("User obj:- " + user.toString());
				break;
			case 5:
			case 6:
				if (!isUserPolitician) {
					break;
				}
			case 7:
				if (!isUserPolitician) {
					break;
				}
			case 0:
			default:

			}
		} while (choice != 0);

	}

}
