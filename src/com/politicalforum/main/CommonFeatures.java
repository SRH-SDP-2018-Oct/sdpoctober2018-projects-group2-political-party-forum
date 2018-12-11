package com.politicalforum.main;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;

public class CommonFeatures {

	private static Scanner sc = new Scanner(System.in);

	public static User joinGroup(User user, PoliticalPartyServices politicalPartyServices) {
		System.out.println("Available groups to join");
		List<Group> groups = politicalPartyServices.browseGroups();
		HashMap<Integer, Group> map = new HashMap<>();
		for (int i = 0; i < groups.size(); i++) {
			map.put((i + 1), groups.get(i));
			System.out.println((i + 1) + ". " + groups.get(i).getGroupName());
		}
		System.out.println("Enter the group number to join:- ");
		int groupNumber = sc.nextInt();
		sc.nextLine();
		if (map.containsKey(groupNumber)) {
			user = politicalPartyServices.joinGroup(user, map.get(groupNumber));
			user.setSelectedGroup(user.getGroups().get(user.getGroups().size() - 1));
			System.out.println(user.getSelectedGroup().getGroupName() + " Group joined");
			GroupFeatures.viewGroup(user, politicalPartyServices);
		}

		return user;
	}

	public static void myGroups(User user, PoliticalPartyServices politicalPartyServices) {
		int choice = 0;
		HashMap<Integer, Group> map = new HashMap<>();
		System.out.println("\n********MY GROUPS*************");
		if (user.getGroups().isEmpty()) {
			System.out.println("\nYou haven't join any group.");
			joinGroup(user, politicalPartyServices);
		} else {
			for (int i = 0; i < user.getGroups().size(); i++) {
				System.out.println((i + 1) + ". " + user.getGroups().get(i).getGroupName());
				map.put((i + 1), user.getGroups().get(i));
			}
			System.out.println("Your Choice:- ");
			choice = sc.nextInt();
			sc.nextLine();
			user.setSelectedGroup(map.get(choice));
			GroupFeatures.viewGroup(user, politicalPartyServices);
		}
	}

}
