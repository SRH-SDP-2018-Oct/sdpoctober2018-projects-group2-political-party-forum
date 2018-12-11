package com.politicalforum.main;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Poll;
import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.Helper;

public class PollFeatures {

	private static Scanner sc = new Scanner(System.in);

	public static void createPoll(User user, PoliticalPartyServices politicalPartyServices) {
		System.out.println("****************Poll*****************");
		System.out.println("Enter Poll Name :");
		String pollName = sc.nextLine();
		System.out.println("\n\n 1.Two Option\n 2.Three");
		System.out.println("Number of Options ? 2 or 3 :");
		int numberOfOptions = sc.nextInt();
		sc.nextLine();
		String optionOne = null;
		String optionTwo = null;
		String optionThree = null;
		switch (numberOfOptions) {
		case 1:
			System.out.println("Enter Option One");
			optionOne = sc.nextLine();
			System.out.println("Enter Option Two");
			optionTwo = sc.nextLine();
			break;
		case 2:
			System.out.println("Enter Option One");
			optionOne = sc.nextLine();
			System.out.println("Enter Option Two");
			optionTwo = sc.nextLine();
			System.out.println("Enter Option Three");
			optionThree = sc.nextLine();
			break;
		default:
			System.out.println("Wrong option!");
			break;
		}
		user = politicalPartyServices.createPoll(user, new Poll(pollName, Helper.getCurrentDateOfTypeJavaSql(),
				optionOne, optionTwo, optionThree, user.getUserId(), user.getSelectedGroup().getGroupId()));
		System.out.println("Poll created:- " + user.getSelectedGroup().getSelectedPoll().toString());
		viewPoll(user, politicalPartyServices);
	}

	public static void listPolls(User user, PoliticalPartyServices politicalPartyServices) {
		System.out.println("\nAvailable Polls\n");
		List<Poll> polls = politicalPartyServices.listPolls(user.getSelectedGroup().getGroupId());
		if(polls.isEmpty()) {
			System.out.println("No Polls created for this group.");
			return;
		}
		HashMap<Integer, Poll> map = new HashMap<>();
		for(int i=0;i<polls.size();i++) {
			System.out.println((i+1)+". "+ polls.get(i).getPollTopic());
			map.put(i+1, polls.get(i));
		}
		System.out.println("Select Poll option:- ");
		int choice = sc.nextInt();
		sc.nextLine();
		user.getSelectedGroup().setSelectedPoll(polls.get(choice));
		viewPoll(user, politicalPartyServices);
	}

	private static void viewPoll(User user, PoliticalPartyServices politicalPartyServices) {

	}

	private static void answerPoll(User user, PoliticalPartyServices politicalPartyServices) {

	}

}
