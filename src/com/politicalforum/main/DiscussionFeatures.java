package com.politicalforum.main;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.GroupDiscussion;
import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;

public class DiscussionFeatures {

	private static Scanner sc = new Scanner(System.in);
	
	public static void createDiscussion(User user, PoliticalPartyServices politicalPartyServices) {
		System.out.println("Enter Discussion Name:- ");
		String groupDiscussionName = sc.nextLine();
		groupDiscussionName.trim();
		System.out.println("Enter Discusson Body:- ");
		String groupDiscussionBody = sc.nextLine();
		groupDiscussionBody.trim();
		politicalPartyServices.createDiscussion(user, groupDiscussionName, groupDiscussionBody);
	}
	
	public static void listAndSelectDiscussion(User user, PoliticalPartyServices politicalPartyServices) {
		int option = 0;
		List<GroupDiscussion> discussions = politicalPartyServices
				.viewAllDiscussions(user.getSelectedGroup().getGroupId());
		if(discussions.isEmpty()) {
			System.out.println("\nThere are no discussions for this group.\n");
			return;
		}
		HashMap<Integer, GroupDiscussion> map = new HashMap<>();
		System.out.println("All Discussions:- \n");
		for (int i = 0; i < discussions.size(); i++) {
			System.out.println((i + 1) + ". " + discussions.get(i).getGroupDiscussionName());
			map.put((i + 1), discussions.get(i));
		}
		System.out.println("\nSelect Discussion:- ");
		option = sc.nextInt();
		sc.nextLine();
		user.getSelectedGroup().setSelectedGroupDiscussion(discussions.get(option - 1));
		viewDiscussion(user, politicalPartyServices);
	}
	
	public static void viewDiscussion(User user, PoliticalPartyServices politicalPartyServices) {

		System.out.println("****************Discussion*****************");

		System.out.println(
				"Disucssion Name:- " + user.getSelectedGroup().getSelectedGroupDiscussion().getGroupDiscussionName());

		System.out.println("\nAbout this Discussion:- "
				+ user.getSelectedGroup().getSelectedGroupDiscussion().getGroupDiscussionBody());

		HashMap<String, Boolean> map = politicalPartyServices
				.getPostedByDetails(user.getSelectedGroup().getSelectedGroupDiscussion().getGroupFollowersId());
		String createdBy = null;
		for (String key : map.keySet()) {
			createdBy = map.get(key) ? "Anonymous" : key;
		}

		System.out.println("\nCreated By:- " + createdBy);

		int choice = 0;
		do {
			System.out.println("\n1.Post Comment\n\n2.View Comments");
			System.out.println("Press 0 to go back.");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				CommentFeatures.postComment(user, politicalPartyServices);
				break;
			case 2:
				CommentFeatures.viewComments(user, politicalPartyServices);
				break;
			default:
				break;
			}
		} while (choice != 0);

	}
	
}
