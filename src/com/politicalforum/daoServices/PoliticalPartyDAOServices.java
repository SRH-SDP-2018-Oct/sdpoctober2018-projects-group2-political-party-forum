package com.politicalforum.daoServices;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.GeneralUser;
import com.politicalforum.beans.Group;
import com.politicalforum.beans.GroupComments;
import com.politicalforum.beans.GroupDiscussion;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.Poll;
import com.politicalforum.beans.Project;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyExistException;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.exceptions.UnknownDateFormatException;
import com.politicalforum.exceptions.UserAlreadyExistsException;

public interface PoliticalPartyDAOServices {
	public User insertUserDetails(GeneralUser user) throws UserAlreadyExistsException;

	public User insertPoliticalUserDetails(PoliticalUser politicalUser) throws UserAlreadyExistsException;

	public List<Group> checkIfGroupWithSimilarNameExists(String groupName);

	public Group insertGroupDetails(Group group)throws GroupAlreadyExistException;

	public User getUser(String emailId, String password) throws InvalidCredentialsException;

	public List<Group> retrieveGroupDetails();
	
	Boolean addFollowerToAGroup(String userId, Group group)throws GroupAlreadyJoinedException;
	
	public List<Group> getUserGroups(String userId);
	
	public Group createDiscussion(String userId, Group group, GroupDiscussion groupDiscussion);
	
	public List<GroupDiscussion> fetchAllDiscussions(String groupId);

	public HashMap<String, Boolean> getPostedByDetails(String groupFollowersId);
	
	public Boolean postComment(User user, String comment);

	public List<GroupComments> viewComments(String discussionId);

	public Group createProject(Group group, Project project);
	
	public Group createPoll(String userId, Group group, Poll poll);

	public Boolean getIfUserIsGroupOwner(String userId, String groupId);
	
	public List<Project> viewProjects(String groupId);
	
	public Group updateProject(Group group, Date newEndDate, String newContractorName);
	
	public List<Poll> viewPolls(String groupId);
	
	public void closeServices();
	
}
