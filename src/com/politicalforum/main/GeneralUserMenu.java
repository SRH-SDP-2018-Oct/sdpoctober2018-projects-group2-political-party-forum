package com.politicalforum.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
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
		case 2:

			break;
		default:
			System.out.println("Wrong Option!");
			break;

		}
	}
}
