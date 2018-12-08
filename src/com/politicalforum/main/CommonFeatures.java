package com.politicalforum.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
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
			System.out.println(user.getSelectedGroup().getGroupName()+" Group joined");
			viewGroup(user, politicalPartyServices);
		}
		
		return user;
	}

	public static void viewGroup(User user, PoliticalPartyServices politicalPartyServices) {
		System.out.println("**********Group Menu************");
		System.out.println("\n\n1. View Discussions\n2. View Polls\n3. View Projects for this group\n4. Create Discussions\n5. Create Polls");
		boolean isUserPolitician = Helper.checkIfUserIsPolitician(user.getUserId());
		if(isUserPolitician) {
			System.out.println("6. Create Project\n7. View Dashboard");
		}
		System.out.println("\n\n Press 0 to go to home page.");
		int choice = sc.nextInt();
		do {
		switch(choice) {
			case 1: 
			case 2:
			case 3:
			case 4: 
			case 5:
			case 6:	if(!isUserPolitician) {
						break;
					}
			case 7: if(!isUserPolitician) {
						break;
					}
			case 0: 
			default:
					
		}
		}while(choice != 0);
		
	}
	
}
