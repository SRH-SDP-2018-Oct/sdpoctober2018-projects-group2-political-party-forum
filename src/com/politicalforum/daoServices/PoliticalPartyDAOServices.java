package com.politicalforum.daoServices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.GeneralUser;
import com.politicalforum.beans.Group;
import com.politicalforum.beans.GroupComments;
import com.politicalforum.beans.GroupDiscussion;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.Project;
import com.politicalforum.beans.User;

public interface PoliticalPartyDAOServices {
	public User insertUserDetails(GeneralUser user) throws SQLException;

	public User insertPoliticalUserDetails(PoliticalUser politicalUser) throws SQLException;

	public List<Group> checkIfGroupWithSimilarNameExists(String groupName) throws SQLException;

	public Group insertGroupDetails(Group group);

	public User getUser(String emailId, String password) throws SQLException;

	public List<Group> retrieveGroupDetails() throws SQLException;
	
	Boolean addFollowerToAGroup(String userId, Group group);
	
	public List<Group> getUserGroups(String userId);
	
	public Group createDiscussion(String userId, Group group, GroupDiscussion groupDiscussion);
	
	public List<GroupDiscussion> fetchAllDiscussions(String groupId);

	public HashMap<String, Boolean> getPostedByDetails(String groupFollowersId);
	
	public Boolean postComment(User user, String comment);

	public List<GroupComments> viewComments(String discussionId);

	public Group createProject(Group group, Project project);
	
}
