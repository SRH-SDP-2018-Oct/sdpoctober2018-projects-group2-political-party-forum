package com.politicalforum.daoServices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.politicalforum.exceptions.GroupAlreadyExistException;
import com.politicalforum.exceptions.GroupAlreadyJoinedException;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.exceptions.PollAlreadyAnsweredException;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.exceptions.UserAlreadyExistsException;
import com.politicalforum.providers.PoliticalPartyConnectionProvider;
import com.politicalforum.utils.Helper;

public class PoliticalPartyDAOServicesImplementation implements PoliticalPartyDAOServices {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public PoliticalPartyDAOServicesImplementation() throws ServiceNotFoundException {
		connection = PoliticalPartyConnectionProvider.getPoliticalForumConnectionServices();
	}

	@Override
	public User insertUserDetails(GeneralUser user) throws UserAlreadyExistsException {
		String userId = null;
		String hashPassword = null;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"insert into userdetails(userid, firstname, lastname, region, emailId, aadharnumber, gender, age, isanonymous) values('U'||user_sequence.nextval,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getRegion());
			preparedStatement.setString(4, user.getEmailId());
			preparedStatement.setString(5, user.getAadharNumber());
			preparedStatement.setString(6, user.getGender());
			preparedStatement.setString(7, String.valueOf(user.getAge()));
			preparedStatement.setInt(8, user.getIsAnonymous() ? 1 : 0);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("select max(userid) from userdetails");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			userId = resultSet.getString(1);
			hashPassword = Helper.generateHashPassword(user.getPassword());
			preparedStatement = connection
					.prepareStatement("insert into usercredentials(userid, hashpassword) values(?,?)");
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, hashPassword);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			throw new UserAlreadyExistsException(
					"User already exist please try again with a different Email ID or Adhaar Card");
		}
		return getUserDetails(userId);
	}

	@Override
	public PoliticalUser insertPoliticalUserDetails(PoliticalUser politicalUser) throws UserAlreadyExistsException {
		String politicalUserId = null;
		String hashPassword = null;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"insert into userdetails(userid, firstname, lastname, region, emailId, politicalid, gender, age, isanonymous) values('P'||user_sequence.nextval,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, politicalUser.getFirstName());
			preparedStatement.setString(2, politicalUser.getLastName());
			preparedStatement.setString(3, politicalUser.getRegion());
			preparedStatement.setString(4, politicalUser.getEmailId());
			preparedStatement.setString(5, politicalUser.getPoliticianId());
			preparedStatement.setString(6, politicalUser.getGender());
			preparedStatement.setString(7, String.valueOf(politicalUser.getAge()));
			preparedStatement.setInt(8, politicalUser.getIsAnonymous() ? 1 : 0);
			preparedStatement.executeUpdate();
			preparedStatement = connection
					.prepareStatement("select max(userid) from userdetails where userid like 'P%'");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			politicalUserId = resultSet.getString(1);
			System.out.println("Political Id:- " + politicalUserId);
			hashPassword = Helper.generateHashPassword(politicalUser.getPassword());
			preparedStatement = connection
					.prepareStatement("insert into usercredentials(userid, hashpassword) values(?,?)");
			preparedStatement.setString(1, politicalUserId);
			preparedStatement.setString(2, hashPassword);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			throw new UserAlreadyExistsException(
					"User already exist please try again with a different Email or Political ID");

		}
		return getPoliticalUserDetails(politicalUserId);
	}

	@Override
	public List<Group> checkIfGroupWithSimilarNameExists(String groupName) {
		List<Group> groups = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement("select UPPER(groupdetailsname) from groupdetails where groupdetailsname like '%"
							+ groupName.toUpperCase() + "%'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				groups.add(new Group(resultSet.getString(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groups;
	}

	@Override
	public Group insertGroupDetails(Group group) throws GroupAlreadyExistException {
		String groupId = null;
		try {
			preparedStatement = connection.prepareStatement(
					"insert into groupdetails(groupdetailsid, groupdetailsname, groupdetailsbody, userid, dateofcreation) values('GD' || groupdetail_sequence.nextval,?,?,?,?)");
			preparedStatement.setString(1, group.getGroupName().toUpperCase());
			preparedStatement.setString(2, group.getGroupDescription());
			preparedStatement.setString(3, group.getGroupOwnerId());
			preparedStatement.setDate(4, group.getGroupCreationTime());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("select max(groupdetailsId) from groupdetails");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				groupId = resultSet.getString(1);
			preparedStatement.close();
			resultSet.close();
			connection.commit();
		} catch (SQLException e) {
			throw new GroupAlreadyExistException(
					"This group already exists please create another group or join that group");
		}
		return new Group(groupId, group.getGroupName(), group.getGroupDescription(), group.getGroupOwnerId(),
				group.getGroupCreationTime());
	}

	@Override
	public User getUser(String emailId, String password) throws InvalidCredentialsException {
		try {
			connection.setAutoCommit(false);
			String userId = getUserId(emailId);
			Boolean isPasswordValid = userId != null ? checkCredentialsForUser(userId, password) : false;
			if (isPasswordValid) {
				return Helper.checkIfUserIsPolitician(userId) ? getPoliticalUserDetails(userId)
						: getUserDetails(userId);
			}
		} catch (SQLException e) {
			throw new InvalidCredentialsException(e);
		}
		return null;
	}

	@Override
	public Boolean addFollowerToAGroup(String userId, Group group) throws GroupAlreadyJoinedException {
		try {
			preparedStatement = connection.prepareStatement(
					"insert into groupfollowers(groupfollowersid, groupdetailsid, userid) values('GF'||groupfollowers_sequence.nextval,?,?)");
			preparedStatement.setString(1, group.getGroupId());
			preparedStatement.setString(2, userId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.commit();
			preparedStatement.close();
			return true;
		} catch (SQLException e) {
			throw new GroupAlreadyJoinedException("You are already in the group");

		}

	}

	private String getUserId(String emailId) throws SQLException, InvalidCredentialsException {
		preparedStatement = connection
				.prepareStatement("select userid from userdetails where emailid='" + emailId + "'");
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getString(1);
		} else {
			throw new InvalidCredentialsException("This ID is not registered with us. Please register first.");
		}
	}

	private User getUserDetails(String userId) {
		try {
			preparedStatement = connection.prepareStatement("select * from userdetails where userid='" + userId + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String region = resultSet.getString(4);
				String emailId = resultSet.getString(5);
				String aadharNumber = resultSet.getString(6);
				String gender = resultSet.getString(7);
				int age = Integer.parseInt(resultSet.getString(8));
				Boolean isAnonymous = resultSet.getInt(9) > 0 ? true : false;
				return new GeneralUser(userId, firstName, lastName, age, emailId, gender, isAnonymous, region,
						new ArrayList<>(), aadharNumber);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PoliticalUser getPoliticalUserDetails(String userId) {
		try {
			preparedStatement = connection.prepareStatement("select * from userdetails where userid='" + userId + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String region = resultSet.getString(4);
				String emailId = resultSet.getString(5);
				String politicianId = resultSet.getString(6);
				String gender = resultSet.getString(7);
				int age = Integer.parseInt(resultSet.getString(8));
				Boolean isAnonymous = resultSet.getInt(9) > 0 ? true : false;
				return new PoliticalUser(userId, firstName, lastName, age, emailId, gender, isAnonymous, region,
						new ArrayList<>(), politicianId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Group> retrieveGroupDetails() {
		List<Group> group = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("select * from groupdetails");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String groupId = resultSet.getString(1);
				String groupName = resultSet.getString(2);
				String groupDescription = resultSet.getString(3);
				String groupOwnerId = resultSet.getString(4);
				Date groupCreationTime = resultSet.getDate(5);
				group.add(new Group(groupId, groupName, groupDescription, groupOwnerId, groupCreationTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;
	}

	private Boolean checkCredentialsForUser(String id, String password)
			throws SQLException, InvalidCredentialsException {
		resultSet = connection.prepareStatement("select hashpassword from usercredentials where userid='" + id + "'")
				.executeQuery();
		resultSet.next();
		if (Helper.isPasswordCorrect(password, resultSet.getString(1))) {
			return true;
		} else {
			throw new InvalidCredentialsException("Invalid Username/Password.");
		}
	}

	@Override
	public List<Group> getUserGroups(String userId) {
		try {
			List<Group> group = new ArrayList<>();
			preparedStatement = connection.prepareStatement(
					"select groupdetailsid, groupdetailsname, groupdetailsbody, dateofcreation, userid from groupdetails where groupdetailsid in (select groupdetailsid from groupfollowers where userid='"
							+ userId + "')");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String groupId = resultSet.getString(1);
				String groupName = resultSet.getString(2);
				String groupDescription = resultSet.getString(3);
				Date groupCreationTime = resultSet.getDate(4);
				String groupOwnerId = resultSet.getString(5);
				group.add(new Group(groupId, groupName, groupDescription, groupOwnerId, groupCreationTime));
			}
			return group;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public Group createDiscussion(String userId, Group group, GroupDiscussion groupDiscussion) {
		try {
			String groupFollowersId = fetchGroupFollowerId(userId, group.getGroupId());
			preparedStatement = connection.prepareStatement(
					"insert into discussion(discussionid, discussionname, discussionbody, groupfollowersid, dateofdiscussion, groupdetailsid) values('D'||discussion_sequence.nextval,?,?,?,?,?)");
			preparedStatement.setString(1, groupDiscussion.getGroupDiscussionName());
			preparedStatement.setString(2, groupDiscussion.getGroupDiscussionBody());
			preparedStatement.setString(3, groupFollowersId);
			preparedStatement.setDate(4, groupDiscussion.getGroupCreationTime());
			preparedStatement.setString(5, group.getGroupId());
			preparedStatement.executeUpdate();
			connection.commit();
			preparedStatement = connection.prepareStatement("select max(discussionid) from discussion");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String groupDiscussionId = resultSet.getString(1);
			groupDiscussion.setGroupDiscussionId(groupDiscussionId);
			group.getGroupDiscussions().add(groupDiscussion);
			insertNotification(userId, groupDiscussion.getGroupDiscussionName().toUpperCase() + " Discussion is Created in Group "
					+ group.getGroupName().toUpperCase(), group.getGroupId());
			return group;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String fetchGroupFollowerId(String userId, String groupId) {
		try {
			preparedStatement = connection.prepareStatement("select groupfollowersid from groupfollowers where userid='"
					+ userId + "' and groupdetailsid='" + groupId + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GroupDiscussion> fetchAllDiscussions(String groupId) {
		List<GroupDiscussion> discussions = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement("select * from discussion where groupdetailsid='" + groupId + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String discussionId = resultSet.getString(1);
				String discussionName = resultSet.getString(2);
				String discussionBody = resultSet.getString(3);
				String groupFollowersId = resultSet.getString(4);
				Date dateOfDiscussion = resultSet.getDate("dateOfDiscussion");
				discussions.add(new GroupDiscussion(discussionId, discussionName, discussionBody, dateOfDiscussion,
						groupFollowersId));
			}
			return discussions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HashMap<String, Boolean> getPostedByDetails(String groupFollowersId) {
		HashMap<String, Boolean> map = new HashMap<>();
		try {
			preparedStatement = connection.prepareStatement(
					"select firstname, isanonymous from userdetails where userid in (select userid from groupfollowers where groupfollowersid='"
							+ groupFollowersId + "')");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				map.put(resultSet.getString(1), Integer.parseInt(resultSet.getString(2)) > 0 ? true : false);
			}
			return map;
		} catch (Exception e) {

		}
		return map;
	}

	@Override
	public Boolean postComment(User user, String comment) {
		try {
			preparedStatement = connection.prepareStatement(
					"insert into comments(commentsid,commentsbody,discussionid,dateofcomment,postedByName) values('C'||comments_sequence.nextval,?,?,?,?)");
			preparedStatement.setString(1, comment);
			preparedStatement.setString(2, user.getSelectedGroup().getSelectedGroupDiscussion().getGroupDiscussionId());
			preparedStatement.setDate(3, Helper.getCurrentDateOfTypeJavaSql());
			preparedStatement.setString(4, user.getIsAnonymous() ? "Anonymous" : user.getFirstName());
			preparedStatement.executeQuery();
			connection.commit();
			preparedStatement.close();
			String name = user.getIsAnonymous() ? "Anonymous" : user.getFirstName();
			insertNotification(user.getUserId(),
					name + " posted a comment in " + user.getSelectedGroup().getGroupName() + " under discussion "
							+ user.getSelectedGroup().getSelectedGroupDiscussion().getGroupDiscussionName(),
					user.getSelectedGroup().getGroupId());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<GroupComments> viewComments(String discussionId) {
		List<GroupComments> comments = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement("select * from comments where discussionid='" + discussionId + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String commentId = resultSet.getString("commentsid");
				String commentBody = resultSet.getString("commentsbody");
				Date commentCreationTime = resultSet.getDate("dateofcomment");
				String commentPostedBy = resultSet.getString("postedbyname");
				comments.add(new GroupComments(commentId, commentBody, commentCreationTime, commentPostedBy));
			}
			return comments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	@Override
	public Group createProject(String userId, Group group, Project project) {
		String groupProgressReportId = null;
		try {
			preparedStatement = connection.prepareStatement(
					"insert into groupprogressreport(groupprogressreportid,workname,workbody,contractor,startdate,enddate,dateofcompletion,fund,groupdetailsid,reportdate) values ('GPR'||groupprogress_sequence.nextval,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, project.getTaskName());
			preparedStatement.setString(2, project.getTaskDescription());
			preparedStatement.setString(3, project.getContractorName());
			preparedStatement.setDate(4, project.getTaskStartDate());
			preparedStatement.setDate(5, project.getTaskEndDate());
			preparedStatement.setDate(6, project.getIntendedCompletionDate());
			preparedStatement.setInt(7, project.getTaskAllocatedFund());
			preparedStatement.setString(8, group.getGroupId());
			preparedStatement.setDate(9, project.getTaskCreationTimestamp());
			preparedStatement.executeUpdate();
			connection.commit();
			preparedStatement.close();
			preparedStatement = connection
					.prepareStatement("select max(groupprogressreportid) from groupprogressreport");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				groupProgressReportId = resultSet.getString(1);
			project.setGroupProgressReportId(groupProgressReportId);
			group.getProjects().add(project);
			group.setSelectedProject(project);
			insertNotification(userId, "A Project with name " + project.getTaskName() + " has been created for Group "
					+ group.getGroupName(), group.getGroupId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;

	}

	@Override
	public Group createPoll(String userId, Group group, Poll poll) {
		try {
			String groupFollowersId = fetchGroupFollowerId(userId, group.getGroupId());
			preparedStatement = connection.prepareStatement(
					"insert into poll(pollid, polltopic, dateofpoll, option1, option2, option3, userid, groupdetailsid, groupfollowersid ) values('POLL' || POLL_SEQUENCE.nextval,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, poll.getPollTopic());
			preparedStatement.setDate(2, Helper.getCurrentDateOfTypeJavaSql());
			preparedStatement.setString(3, poll.getOption1());
			preparedStatement.setString(4, poll.getOption2());
			preparedStatement.setString(5, poll.getOption3());
			preparedStatement.setString(6, userId);
			preparedStatement.setString(7, group.getGroupId());
			preparedStatement.setString(8, groupFollowersId);
			preparedStatement.execute();
			connection.commit();
			preparedStatement = connection.prepareStatement("select max(pollid) from poll");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String pollId = resultSet.getString(1);
			poll.setPollId(pollId);
			group.getPolls().add(poll);
			group.setSelectedPoll(poll);
			insertNotification(userId, "A Poll has been created in Group " + group.getGroupName(), group.getGroupId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;

	}

	@Override
	public void closeServices() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Boolean getIfUserIsGroupOwner(String userId, String groupId) {
		try {
			preparedStatement = connection
					.prepareStatement("select userid from groupdetails where groupdetailsid='" + groupId + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return userId.equalsIgnoreCase(resultSet.getString(1));
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Project> viewProjects(String groupId) {
		List<Project> projects = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement("select * from groupprogressreport where groupdetailsid='" + groupId + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String groupProgressReportId = resultSet.getString("GROUPPROGRESSREPORTID");
				String taskName = resultSet.getString("WORKNAME");
				String taskDescription = resultSet.getString("WORKBODY");
				Date taskStartDate = resultSet.getDate("STARTDATE");
				Date taskEndDate = resultSet.getDate("ENDDATE");
				Date intendedCompletionDate = resultSet.getDate("DATEOFCOMPLETION");
				int taskAllocatedFund = resultSet.getInt("FUND");
				Date taskCreationTimestamp = resultSet.getDate("REPORTDATE");
				String contractorName = resultSet.getString("CONTRACTOR");
				projects.add(
						new Project(groupProgressReportId, taskName, taskStartDate, taskEndDate, intendedCompletionDate,
								taskDescription, taskAllocatedFund, taskCreationTimestamp, contractorName));
			}
			return projects;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public Group updateProject(Group group, Date newEndDate, String newContractorName) {
		try {
			if (newContractorName != null) {
				preparedStatement = connection.prepareStatement(
						"update groupprogressreport set contractor='" + newContractorName + "' where groupprogressid='"
								+ group.getSelectedProject().getGroupProgressReportId() + "'");

			} else {
				preparedStatement = connection.prepareStatement("update groupprogressreport set enddate='" + newEndDate
						+ "' where groupprogressid='" + group.getSelectedProject().getGroupProgressReportId() + "'");
			}
			preparedStatement.executeUpdate();
			connection.commit();
			preparedStatement = connection
					.prepareStatement("select contractor,enddate from groupprogressreport where groupprogressid='"
							+ group.getSelectedProject().getGroupProgressReportId() + "'");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			group.getSelectedProject().setContractorName(resultSet.getString("contractor"));
			group.getSelectedProject().setTaskEndDate(resultSet.getDate("enddate"));
			return group;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;
	}

	@Override
	public List<Poll> viewPolls(String groupId) {
		List<Poll> polls = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement("select * from poll where groupdetailsid='" + groupId + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String pollId = resultSet.getString("POLLID");
				String pollTopic = resultSet.getString("POLLTOPIC");
				Date dateOfPoll = resultSet.getDate("DATEOFPOLL");
				String option1 = resultSet.getString("OPTION1");
				String option2 = resultSet.getString("OPTION2");
				String option3 = resultSet.getString("OPTION3");
				String userId = resultSet.getString("USERID");
				String groupFollowersId = resultSet.getString("GROUPFOLLOWERSID");
				polls.add(new Poll(pollId, pollTopic, dateOfPoll, option1, option2, option3, userId, groupId,
						groupFollowersId));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return polls;
	}

	@Override
	public List<Notification> fetchNotifications(String userId) {
		List<String> notificationIds = getNotificationBody(userId);
		List<Notification> notifications = new ArrayList<>();
		for (String id : notificationIds) {
			try {
				preparedStatement = connection.prepareStatement(
						"select notificationbody,dateofnotification from notifications where notificationid='" + id
								+ "'");
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					notifications.add(new Notification(id, resultSet.getString("NOTIFICATIONBODY"),
							resultSet.getDate("DATEOFNOTIFICATION")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return notifications;
	}

	private List<String> getNotificationBody(String userId) {
		List<String> notificationIds = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(
					"select notificationid from usernotification where userid='" + userId + "' and isread=0");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				notificationIds.add(resultSet.getString(1));
			}
			updateNotifications(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notificationIds;
	}

	@Override
	public void updateNotifications(String userId) {
		try {
			preparedStatement = connection.prepareStatement(
					"update usernotification set isread=1 where userid='" + userId + "' and isread=0");
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertNotification(String userId, String notificationBody, String groupId) {
		String notificationId = null;
		try {
			preparedStatement = connection.prepareStatement(
					"insert into notifications(notificationid,notificationbody,dateofnotification,groupdetailsid) values ('N'||notification_sequence.nextval,?,?,?)");
			preparedStatement.setString(1, notificationBody);
			preparedStatement.setDate(2, Helper.getCurrentDateOfTypeJavaSql());
			preparedStatement.setString(3, groupId);
			preparedStatement.executeQuery();
			connection.commit();
			preparedStatement = connection.prepareStatement("select max(notificationid) from notifications");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			notificationId = resultSet.getString(1);
			List<String> userIds = fetchUserId(groupId);
			for (String id : userIds) {
				insertUserNotifications(id, notificationId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean answerPoll(User user, PollAnswer pollAnswer) throws PollAlreadyAnsweredException {
		try {
			preparedStatement = connection.prepareStatement(
					"insert into pollanswer(pollanswerid,pollid,userid,answer) values('PA'||pollanswer_sequence.nextval,?,?,?)");
			preparedStatement.setString(1, pollAnswer.getPollId());
			preparedStatement.setString(2, user.getUserId());
			preparedStatement.setString(3, pollAnswer.getAnswer());
			preparedStatement.executeUpdate();
			connection.commit();
			return true;
		} catch (SQLException e) {
			throw new PollAlreadyAnsweredException("You have already answered this poll.");
		}
	}

	private List<String> fetchUserId(String groupId) {
		List<String> userIds = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement("select userid from groupfollowers where groupdetailsid='" + groupId + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userIds.add(resultSet.getString("USERID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userIds;
	}

	private void insertUserNotifications(String userId, String notificationId) {
		try {
			preparedStatement = connection.prepareStatement(
					"insert into usernotification(usernotificationid,notificationid,userid,isread) values (usernotification_sequence.nextval,?,?,0)");
			preparedStatement.setString(1, notificationId);
			preparedStatement.setString(2, userId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
