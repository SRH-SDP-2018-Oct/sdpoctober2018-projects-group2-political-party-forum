package com.politicalforum.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.GroupComments;
import com.politicalforum.beans.GroupDiscussion;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.ServiceNotFoundException;

public interface PoliticalPartyServices {

	public User registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, String password)
			throws ServiceNotFoundException, SQLException;

	public User registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId,
			String gender, String politicianId, Boolean isAnonymous, String region, String password)
			throws ServiceNotFoundException, SQLException;

	public User login(String emailId, String password) throws SQLException;

	public User createGroup(String groupName, String groupDescription, User user);

	public List<Group> browseGroups() throws SQLException;

	public List<Group> checkIfGroupExistsWithSimilarNames(String groupName) throws SQLException;

	public User joinGroup(User user, Group group);
	
	public User createDiscussion(User user, String groupDiscussionName, String groupDiscussionBody);
	
	public List<GroupDiscussion> viewAllDiscussions(String groupId);
	
	public HashMap<String, Boolean> getPostedByDetails(String groupFollowersId);

	public Boolean postComment(User user, String comment);
	
	public List<GroupComments> viewComments(String discussionId);
	
}
