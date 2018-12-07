package com.politicalforum.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;

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
			System.out.println("User now :- "+user.toString());
		}
		
		return user;
	}
	
}
