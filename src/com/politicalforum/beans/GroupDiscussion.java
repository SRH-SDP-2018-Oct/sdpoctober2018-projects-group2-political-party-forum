package com.politicalforum.beans;

import java.sql.Date;

public class GroupDiscussion {
	private String groupDiscussionId;
	private String groupDiscussionName;
	private String groupDiscussionBody;
	private Date groupCreationTime;
	private String groupFollowersId;
	
	public GroupDiscussion() {
	}

	public GroupDiscussion(String groupDiscussionName, String groupDiscussionBody, Date groupCreationTime) {
		super();
		this.groupDiscussionName = groupDiscussionName;
		this.groupDiscussionBody = groupDiscussionBody;
		this.groupCreationTime = groupCreationTime;
	}

	public GroupDiscussion(String groupDiscussionId, String groupDiscussionName, String groupDiscussionBody,
			Date groupCreationTime, String groupFollowersId) {
		super();
		this.groupDiscussionId = groupDiscussionId;
		this.groupDiscussionName = groupDiscussionName;
		this.groupDiscussionBody = groupDiscussionBody;
		this.groupCreationTime = groupCreationTime;
		this.groupFollowersId = groupFollowersId;
	}

	public String getGroupDiscussionId() {
		return groupDiscussionId;
	}

	public void setGroupDiscussionId(String groupDiscussionId) {
		this.groupDiscussionId = groupDiscussionId;
	}

	public String getGroupDiscussionName() {
		return groupDiscussionName;
	}

	public void setGroupDiscussionName(String groupDiscussionName) {
		this.groupDiscussionName = groupDiscussionName;
	}

	public String getGroupDiscussionBody() {
		return groupDiscussionBody;
	}

	public void setGroupDiscussionBody(String groupDiscussionBody) {
		this.groupDiscussionBody = groupDiscussionBody;
	}

	public Date getGroupCreationTime() {
		return groupCreationTime;
	}

	public void setGroupCreationTime(Date groupCreationTime) {
		this.groupCreationTime = groupCreationTime;
	}
	
	public String getGroupFollowersId() {
		return groupFollowersId;
	}

	public void setGroupFollowersId(String groupFollowersId) {
		this.groupFollowersId = groupFollowersId;
	}

	@Override
	public String toString() {
		return "GroupDiscussion [groupDiscussionId=" + groupDiscussionId + ", groupDiscussionName="
				+ groupDiscussionName + ", groupDiscussionBody=" + groupDiscussionBody + ", groupCreationTime="
				+ groupCreationTime + ", groupFollowersId=" + groupFollowersId + "]";
	}

}
