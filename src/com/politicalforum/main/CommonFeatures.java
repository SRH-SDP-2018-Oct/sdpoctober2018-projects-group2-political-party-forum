package com.politicalforum.main;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.Notification;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.services.PoliticalPartyServices;

public class CommonFeatures {

	private static Scanner sc = new Scanner(System.in);

	public static void joinGroup(User user, PoliticalPartyServices politicalPartyServices)
			throws GroupAlreadyJoinedException {
		System.out.println("\nAvailable groups to join:-");
		List<Group> groups = politicalPartyServices.browseGroups();
		if (groups.isEmpty()) {
			System.out.println("\nNo groups are available to join at the moment.\n");
			return;
		}
		HashMap<Integer, Group> map = new HashMap<>();
		for (int i = 0; i < groups.size(); i++) {
			map.put((i + 1), groups.get(i));
			System.out.println((i + 1) + ". " + groups.get(i).getGroupName());
		}
		int groupNumber = 0;
		do {
			System.out.println("\nEnter the group number to join:- ");
			groupNumber = sc.nextInt();
		} while (groupNumber < 1 || groupNumber > groups.size());
		sc.nextLine();
		if (map.containsKey(groupNumber)) {
			user = politicalPartyServices.joinGroup(user, map.get(groupNumber));
			user.setSelectedGroup(user.getGroups().get(user.getGroups().size() - 1));
			System.out.println("\n" + user.getSelectedGroup().getGroupName() + " Group joined\n");
			GroupFeatures.viewGroup(user, politicalPartyServices);
		}
	}

	public static void myGroups(User user, PoliticalPartyServices politicalPartyServices)
			throws GroupAlreadyJoinedException {
		int choice = 0;
		HashMap<Integer, Group> map = new HashMap<>();
		System.out.println("\n--------------MY GROUPS-----------------");
		if (user.getGroups().isEmpty()) {
			System.out.println("\nYou haven't join any group.\n");
			joinGroup(user, politicalPartyServices);
		} else {
			for (int i = 0; i < user.getGroups().size(); i++) {
				System.out.println((i + 1) + ". " + user.getGroups().get(i).getGroupName());
				map.put((i + 1), user.getGroups().get(i));
			}
			do {
				System.out.println("Your Choice:- ");
				choice = sc.nextInt();
			} while (choice < 1 || choice > user.getGroups().size());
			sc.nextLine();
			user.setSelectedGroup(map.get(choice));
			GroupFeatures.viewGroup(user, politicalPartyServices);
		}
	}

	public static void notificationView(User user, PoliticalPartyServices politicalPartyServices) {
		List<Notification> notifications = politicalPartyServices.getNotifications(user.getUserId());
		System.out.println("----------------------Notifications-----------------------\n");
		if (notifications.isEmpty()) {
			System.out.println("\nNo Notifications at the moment.\n");
			return;
		}
		for (int i = 0; i < notifications.size(); i++) {
			System.out.println((i + 1) + ". " + notifications.get(i).getNotificationBody() + "::: dated:- "
					+ notifications.get(i).getNotificationCreationTime() + "\n");
		}
		return;
	}

	public static void updateProfile(User user, PoliticalPartyServices politicalPartyServices) {
		System.out.println("\n--------------Update Profile-------------------");
		String choice = null;
		do {
			System.out.println("\nChange your current anonymous to " + !user.getIsAnonymous() + "?(y/n)");
			choice = sc.nextLine();
		} while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")));
		if (choice.equalsIgnoreCase("y")) {
			user = politicalPartyServices.updateProfile(user);
			System.out.println("Profile Updated!");
		} else {
			return;
		}
	}

}
