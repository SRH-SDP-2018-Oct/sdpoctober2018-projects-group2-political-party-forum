package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.beans.User;
import com.politicalforum.exceptions.UnknownDateFormatException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.Helper;

public class GroupFeatures {

	private static Scanner sc = new Scanner(System.in);

	public static void viewGroup(User user, PoliticalPartyServices politicalPartyServices) {
		int choice = 0;
		do {
			try {
				boolean isUserPoliticianAndGroupOwner = Helper.checkIfUserIsPolitician(user.getUserId())
						&& politicalPartyServices.checkIfUserIsGroupOwner(user.getUserId(),
								user.getSelectedGroup().getGroupId());
				System.out.println("---------------Group Menu------------------");
				System.out.println("\nGroup Name:- " + user.getSelectedGroup().getGroupName());
				System.out.println("\nAbout This Group:- " + user.getSelectedGroup().getGroupDescription());
				System.out.println("\nGroup Created By:- "+ (isUserPoliticianAndGroupOwner?"You":(user.getFirstName()+" "+user.getLastName())));
				System.out.println(
						"\n\n1. View Discussions\n2. View Polls\n3. View Projects for this group\n4. Create Discussions\n5. Create Polls");
				if (isUserPoliticianAndGroupOwner) {
					System.out.println("6. Create Project\n");
				}
				System.out.println("\n\nPress 0 to go to home page.");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1: // View Discussions
					DiscussionFeatures.listAndSelectDiscussion(user, politicalPartyServices);
					break;
				case 2: // View Polls
					PollFeatures.listPolls(user, politicalPartyServices);
					break;
				case 3: // View Projects
					ProjectFeatures.listProjects(user, politicalPartyServices, isUserPoliticianAndGroupOwner);
					break;
				case 4: // Create Discussions
					DiscussionFeatures.createDiscussion(user, politicalPartyServices);
					break;
				case 5: // Create Polls
					PollFeatures.createPoll(user, politicalPartyServices);
					break;
				case 6: // Create Project
					if (!isUserPoliticianAndGroupOwner) {
						System.out.println("Wrong Option!");
						break;
					}
					ProjectFeatures.createProject(user, politicalPartyServices, isUserPoliticianAndGroupOwner);
					break;
				case 0:
					break;
				default:
					System.out.println("Wrong Option!");
					break;
				}
			} catch (UnknownDateFormatException e) {
				System.err.println(e.getMessage());
			}
		} while (choice != 0);

	}

}
