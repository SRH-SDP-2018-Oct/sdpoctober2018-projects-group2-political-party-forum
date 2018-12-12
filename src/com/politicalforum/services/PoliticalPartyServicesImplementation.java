package com.politicalforum.services;

import java.sql.Date;
import java.util.ArrayList;
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
import com.politicalforum.daoServices.PoliticalPartyDAOServices;
import com.politicalforum.exceptions.GroupAlreadyExistException;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.exceptions.PollAlreadyAnsweredException;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.exceptions.UserAlreadyExistsException;
import com.politicalforum.providers.PoliticalPartyDAOServicesProvider;
import com.politicalforum.utils.Helper;

public class PoliticalPartyServicesImplementation implements PoliticalPartyServices {
	PoliticalPartyDAOServices politicalPartyDaoServices;

	public PoliticalPartyServicesImplementation() throws ServiceNotFoundException{
		politicalPartyDaoServices = PoliticalPartyDAOServicesProvider.getPoliticalPartyDAOServicesImplementor();
	}

	@Override
	public User registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, String password)
			throws UserAlreadyExistsException {
		return politicalPartyDaoServices.registerUserDetails(new GeneralUser(firstName, lastName, age, emailId, gender,
				isAnonymous, region, password, new ArrayList<>(), aadharNumber));
	}

	@Override
	public User registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String politicianId, Boolean isAnonymous, String region, String password)
			throws UserAlreadyExistsException {
		return politicalPartyDaoServices.registerPoliticalUserDetails(new PoliticalUser(firstName, lastName, age, emailId,
				gender, isAnonymous, region, password, new ArrayList<>(), politicianId));
	}

	@Override
	public List<Group> checkIfGroupExistsWithSimilarNames(String groupName) {
		return politicalPartyDaoServices.checkIfGroupExistsWithSimilarNames(groupName);
	}

	@Override
	public User createGroup(String groupName, String groupDescription, User user)throws GroupAlreadyExistException {
		if (Helper.checkIfUserIsPolitician(user.getUserId())) {
			Group group = politicalPartyDaoServices.createGroup(
					new Group(groupName, groupDescription, user.getUserId(), Helper.getCurrentDateOfTypeJavaSql()));
			
			try {
				if (politicalPartyDaoServices.joinGroup(user.getUserId(), group)) {
					user.getGroups().add(group);
					return user;
				}
			} catch (GroupAlreadyJoinedException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public User login(String emailId, String password) throws InvalidCredentialsException {
		User user = politicalPartyDaoServices.login(emailId, password);
		user.setGroups(politicalPartyDaoServices.getUserGroups(user.getUserId()));
		return user;
	}

	@Override
	public List<Group> browseGroups() {
		return politicalPartyDaoServices.browseGroups();
	}

	@Override
	public User joinGroup(User user, Group group) throws GroupAlreadyJoinedException {
		if (politicalPartyDaoServices.joinGroup(user.getUserId(), group)) {
			user.getGroups().add(group);
			return user;
		}
		return user;
	}

	@Override
	public User createDiscussion(User user, String groupDiscussionName, String groupDiscussionBody) {
		user.setSelectedGroup(politicalPartyDaoServices.createDiscussion(user.getUserId(), user.getSelectedGroup(),
				new GroupDiscussion(groupDiscussionName, groupDiscussionBody, Helper.getCurrentDateOfTypeJavaSql())));
		return user;
	}

	@Override
	public List<GroupDiscussion> viewAllDiscussions(String groupId) {
		return politicalPartyDaoServices.viewAllDiscussions(groupId);
	}

	@Override
	public HashMap<String, Boolean> getPostedByDetails(String groupFollowersId) {
		return politicalPartyDaoServices.getPostedByDetails(groupFollowersId);
	}

	@Override
	public Boolean postComment(User user, String comment) {
		return politicalPartyDaoServices.postComment(user, comment) ;
	}

	@Override
	public List<GroupComments> viewComments(String discussionId) {
		
		return politicalPartyDaoServices.viewComments(discussionId);
	}

	@Override
	public User createProject(User user, Project project) {
		user.setSelectedGroup(politicalPartyDaoServices.createProject(user.getUserId() ,user.getSelectedGroup(), project));
		return user; 
	}

	@Override
	public User createPoll(User user, Poll poll) {
		user.setSelectedGroup(politicalPartyDaoServices.createPoll(user.getUserId(), user.getSelectedGroup(), poll));
		return user;
	}

	@Override
	public Boolean answerPoll(User user, PollAnswer pollAnswer) throws PollAlreadyAnsweredException {

		return politicalPartyDaoServices.answerPoll(user, pollAnswer);
	}

	@Override
	public void exit() {
		politicalPartyDaoServices.closeServices();
	}

	@Override
	public Boolean checkIfUserIsGroupOwner(String userId, String groupId) {
		return politicalPartyDaoServices.checkIfUserIsGroupOwner(userId, groupId);
	}

	@Override
	public List<Project> viewProjects(String groupId) {
		return politicalPartyDaoServices.viewProjects(groupId);
	}

	@Override
	public Group updateProject(Group group, Date newEndDate, String newContractorName) {
		return politicalPartyDaoServices.updateProject(group, newEndDate, newContractorName);
	}

	@Override
	public List<Poll> listPolls(String groupId) {
		return politicalPartyDaoServices.listPolls(groupId);
	}

	@Override
	public List<Notification> getNotifications(String userId) {
		return politicalPartyDaoServices.getNotifications(userId);
	}

	@Override
	public User updateProfile(User user) {
		return politicalPartyDaoServices.updateProfile(user);
	}

}
