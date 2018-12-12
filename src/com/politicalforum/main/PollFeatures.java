package com.politicalforum.main;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Poll;
import com.politicalforum.beans.PollAnswer;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.PollAlreadyAnsweredException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.Helper;

public class PollFeatures {

	private static Scanner sc = new Scanner(System.in);

	public static void createPoll(User user, PoliticalPartyServices politicalPartyServices) {
		System.out.println("---------------Poll-----------------");
		System.out.println("Enter Poll Name :");
		String pollName = sc.nextLine();
		int numberOfOptions = 0;
		do {
			System.out.println("Number of options in this poll(Min.2 and Max 3):- ");
			numberOfOptions = sc.nextInt();
		} while (numberOfOptions != 2 || numberOfOptions != 3);
		sc.nextLine();
		String optionOne = null;
		String optionTwo = null;
		String optionThree = null;
		switch (numberOfOptions) {
		case 2:
			System.out.println("Enter Option One");
			optionOne = sc.nextLine();
			System.out.println("Enter Option Two");
			optionTwo = sc.nextLine();
			break;
		case 3:
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
		System.out.println("\n---------------Available Polls---------------\n");
		List<Poll> polls = politicalPartyServices.listPolls(user.getSelectedGroup().getGroupId());
		if (polls.isEmpty()) {
			System.out.println("\nNo Polls created for this group.\n");
			return;
		}
		HashMap<Integer, Poll> map = new HashMap<>();
		for (int i = 0; i < polls.size(); i++) {
			System.out.println((i + 1) + ". " + polls.get(i).getPollTopic());
			map.put(i + 1, polls.get(i));
		}
		int choice = 0;
		do {
			System.out.println("Select Poll option:- ");
			choice = sc.nextInt();
		} while (choice < 1 || choice > polls.size());
		sc.nextLine();

		user.getSelectedGroup().setSelectedPoll(polls.get(choice - 1));
		viewPoll(user, politicalPartyServices);
	}

	private static void viewPoll(User user, PoliticalPartyServices politicalPartyServices) {
		Poll selectedPoll = user.getSelectedGroup().getSelectedPoll();
		HashMap<Integer, String> map = new HashMap<>();
		HashMap<String, Boolean> postedBy = new HashMap<>();
		postedBy = politicalPartyServices.getPostedByDetails(selectedPoll.getGroupFollowersId());
		String createdBy = null;
		for (String key : postedBy.keySet()) {
			createdBy = postedBy.get(key) ? "Anonymous" : key;
		}
		System.out.println("\n----------------View Poll-------------------");
		System.out.println("\nPoll Topic:- " + selectedPoll.getPollTopic());
		System.out.println("\nPoll Created By:- " + createdBy);
		System.out.println("\nPoll Created On:- " + selectedPoll.getDateOfPoll());
		System.out.println("\nOptions:-");
		System.out.println("\n1. " + selectedPoll.getOption1());
		map.put(1, selectedPoll.getOption1());
		System.out.println("2. " + selectedPoll.getOption2());
		map.put(2, selectedPoll.getOption2());
		if (selectedPoll.getOption3() != null) {
			System.out.println("3. " + selectedPoll.getOption3());
			map.put(3, selectedPoll.getOption3());
		}
		System.out.println("\nEnter Option to answer Poll:-");
		int option = sc.nextInt();
		sc.nextLine();
		try {
			if (politicalPartyServices.answerPoll(user,
					new PollAnswer(selectedPoll.getPollId(), user.getUserId(), map.get(option)))) {
				System.out.println("Thank you for answering.");
			}
		} catch (PollAlreadyAnsweredException e) {
			System.out.println(e.getMessage());
		}
	}

}
