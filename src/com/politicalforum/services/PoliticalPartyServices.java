package com.politicalforum.services;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.GroupComments;
import com.politicalforum.beans.GroupDiscussion;
import com.politicalforum.beans.Notification;
import com.politicalforum.beans.Poll;
import com.politicalforum.beans.PollAnswer;
import com.politicalforum.beans.Project;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyExistException;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.exceptions.PollAlreadyAnsweredException;
import com.politicalforum.exceptions.UserAlreadyExistsException;

public interface PoliticalPartyServices {

	public User registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, String password)
			throws UserAlreadyExistsException;

	public User registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId,
			String gender, String politicianId, Boolean isAnonymous, String region, String password)
			throws UserAlreadyExistsException;

	public User login(String emailId, String password) throws InvalidCredentialsException;

	public User createGroup(String groupName, String groupDescription, User user) throws GroupAlreadyExistException;

	public List<Group> browseGroups();

	public List<Group> checkIfGroupExistsWithSimilarNames(String groupName);

	public User joinGroup(User user, Group group) throws GroupAlreadyJoinedException;
	
	public User createDiscussion(User user, String groupDiscussionName, String groupDiscussionBody);
	
	public List<GroupDiscussion> viewAllDiscussions(String groupId);
	
	public HashMap<String, Boolean> getPostedByDetails(String groupFollowersId);

	public Boolean postComment(User user, String comment);
	
	public List<GroupComments> viewComments(String discussionId);
	
	public User createProject(User user, Project project);
	
	public User createPoll(User user, Poll poll);
	
	public Boolean answerPoll(User user, PollAnswer pollAnswer) throws PollAlreadyAnsweredException;
	
	public Boolean checkIfUserIsGroupOwner(String userId, String groupId);
	
	public List<Project> viewProjects(String groupId);
	
	public Group updateProject(Group group, Date newEndDate, String newContractorName);
	
	public List<Poll> listPolls(String groupId); 
	
	public List<Notification> getNotifications(String userId);
	
	public void exit();
}
