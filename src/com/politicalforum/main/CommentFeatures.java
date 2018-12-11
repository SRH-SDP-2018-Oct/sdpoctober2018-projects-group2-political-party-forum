package com.politicalforum.main;

import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.GroupComments;
import com.politicalforum.beans.User;
import com.politicalforum.services.PoliticalPartyServices;

public class CommentFeatures {

	private static Scanner sc = new Scanner(System.in);
	
	public static void postComment(User user, PoliticalPartyServices politicalPartyServices) {

		System.out.println("Write your comment here:- ");
		String comment = sc.nextLine();
		comment.trim();
		if (politicalPartyServices.postComment(user, comment)) {
			System.out.println("Comment Posted!");
			viewComments(user, politicalPartyServices);
		}

	}

	public static void viewComments(User user, PoliticalPartyServices politicalPartyServices) {
		List<GroupComments> comments = politicalPartyServices
				.viewComments(user.getSelectedGroup().getSelectedGroupDiscussion().getGroupDiscussionId());
		if (comments.isEmpty()) {
			System.out.println("No comments posted in this discussion.");
		}

		for (int i = 0; i < comments.size(); i++) {
			System.out.println((i + 1) + ". " + comments.get(i).getCommentBody());
			System.out.println("Posted By:- " + comments.get(i).getCommentPostedBy() + "\t On "
					+ comments.get(i).getCommentCreationTime());
		}
	}
	
}
