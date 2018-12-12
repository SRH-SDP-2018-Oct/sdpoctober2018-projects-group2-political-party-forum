package com.politicalforum.daoServices;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.GeneralUser;
import com.politicalforum.beans.Group;
import com.politicalforum.beans.GroupComments;
import com.politicalforum.beans.GroupDiscussion;
import com.politicalforum.beans.Notification;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.Poll;
import com.politicalforum.beans.PollAnswer;
import com.politicalforum.beans.Project;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyExistException;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.exceptions.PollAlreadyAnsweredException;
import com.politicalforum.exceptions.UserAlreadyExistsException;

public interface PoliticalPartyDAOServices {
	public User registerUserDetails(GeneralUser user) throws UserAlreadyExistsException;

	public User registerPoliticalUserDetails(PoliticalUser politicalUser) throws UserAlreadyExistsException;

	public List<Group> checkIfGroupExistsWithSimilarNames(String groupName);

	public Group createGroup(Group group)throws GroupAlreadyExistException;

	public User login(String emailId, String password) throws InvalidCredentialsException;

	public List<Group> browseGroups();
	
	Boolean joinGroup(String userId, Group group)throws GroupAlreadyJoinedException;
	
	public List<Group> getUserGroups(String userId);
	
	public Group createDiscussion(String userId, Group group, GroupDiscussion groupDiscussion);
	
	public List<GroupDiscussion> viewAllDiscussions(String groupId);

	public HashMap<String, Boolean> getPostedByDetails(String groupFollowersId);
	
	public Boolean postComment(User user, String comment);

	public List<GroupComments> viewComments(String discussionId);

	public Group createProject(String userId, Group group, Project project);
	
	public Group createPoll(String userId, Group group, Poll poll);

	public Boolean checkIfUserIsGroupOwner(String userId, String groupId);
	
	public List<Project> viewProjects(String groupId);
	
	public Group updateProject(Group group, Date newEndDate, String newContractorName);
	
	public List<Poll> listPolls(String groupId);

	public List<Notification> getNotifications(String userId);
	
	public void updateNotifications(String userId);
	
	public Boolean answerPoll(User user, PollAnswer pollAnswer) throws PollAlreadyAnsweredException;
	
	public User updateProfile(User user);
	
	public void closeServices();
	
}
