package com.politicalforum.main;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.GroupComments;
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
		sc.nextLine();
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
		sc.nextLine();
		user.setSelectedGroup(map.get(choice));
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
			sc.nextLine();
			switch (choice) {
			case 1:
				List<GroupDiscussion> discussions = politicalPartyServices
						.viewAllDiscussions(user.getSelectedGroup().getGroupId());
				HashMap<Integer, GroupDiscussion> map = new HashMap<>();
				System.out.println("All Discussions:- ");
				for (int i = 0; i < discussions.size(); i++) {
					System.out.println((i + 1) + ". " + discussions.get(i).getGroupDiscussionName());
					map.put((i + 1), discussions.get(i));
				}
				System.out.println("Select Discussion:- ");
				choice = sc.nextInt();
				sc.nextLine();
				user.getSelectedGroup().setSelectedGroupDiscussion(discussions.get(choice - 1));
				viewDiscussion(user, politicalPartyServices);
				break;
			case 2:
			case 3:
			case 4:
				System.out.println("Enter Discussion Name:- ");
				String groupDiscussionName = sc.nextLine();
				System.out.println("Enter Discusson Body:- ");
				String groupDiscussionBody = sc.nextLine();
				politicalPartyServices.createDiscussion(user, groupDiscussionName, groupDiscussionBody);
				break;
			case 5:
			case 6:
				if (!isUserPolitician) {
					System.out.println("Wrong Option!");
					break;
				}
//				private int groupProgressReportId;
//				private String taskName;
//				private String taskStartDate;
//				private String taskEndDate;
//				private String intendedCompletionDate;
//				private String taskDescription;
//				private double taskAllocatedFund;
//				private String taskCreationTimestamp;
//				private String contractorName;
				System.out.println("\n------Create Project------------");
				System.out.println("Enter Project Name:- ");
				String taskName = sc.nextLine();
				System.out.println("Enter Project Description:- ");
				String taskDescription = sc.nextLine();
				System.out.println("Enter Project Start Date(DD/MM/YYYY):- ");
				//Date taskStartDate = sc.next();
			case 7:
				if (!isUserPolitician) {
					System.out.println("Wrong Option!");
					break;
				}
			case 0:
			default: 
				System.out.println("Wrong Option!");
				break;

			}
		} while (choice != 0);

	}

	private static void viewDiscussion(User user, PoliticalPartyServices politicalPartyServices) {

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
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				postComment(user, politicalPartyServices);
				break;
			case 2:
				viewComments(user, politicalPartyServices);
				break;
			default:
				break;
			}
		} while (choice != 0);

	}

	private static void postComment(User user, PoliticalPartyServices politicalPartyServices) {

		System.out.println("Write your comment here:- ");
		String comment = sc.nextLine();
		if (politicalPartyServices.postComment(user, comment)) {
			System.out.println("Comment Posted!");
			viewComments(user, politicalPartyServices);
		}

	}

	private static void viewComments(User user, PoliticalPartyServices politicalPartyServices) {
		List<GroupComments> comments = politicalPartyServices
				.viewComments(user.getSelectedGroup().getSelectedGroupDiscussion().getGroupDiscussionId());
		if(comments.isEmpty()) {
			System.out.println("No comments posted in this discussion.");
		}
		
		for (int i = 0; i < comments.size(); i++) {
			System.out.println((i + 1) + ". " + comments.get(i).getCommentBody());
			System.out.println("Posted By:- " + comments.get(i).getCommentPostedBy() + "\t On "
					+ comments.get(i).getCommentCreationTime());
		}
	}

}
