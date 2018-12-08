package com.politicalforum.beans;

import java.sql.Date;

public class GroupDiscussion {
	private String groupDiscussionId;
	private String groupDiscussionName;
	private String groupDiscussionBody;
	private Date groupCreationTime;

	public GroupDiscussion() {
	}

	public GroupDiscussion(String groupDiscussionId, String groupDiscussionName, String groupDiscussionBody,
			Date groupCreationTime) {
		super();
		this.groupDiscussionId = groupDiscussionId;
		this.groupDiscussionName = groupDiscussionName;
		this.groupDiscussionBody = groupDiscussionBody;
		this.groupCreationTime = groupCreationTime;
	}

	public GroupDiscussion(String groupDiscussionName, String groupDiscussionBody, Date groupCreationTime) {
		super();
		this.groupDiscussionName = groupDiscussionName;
		this.groupDiscussionBody = groupDiscussionBody;
		this.groupCreationTime = groupCreationTime;
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

	@Override
	public String toString() {
		return "GroupDiscussion [groupDiscussionId=" + groupDiscussionId + ", groupDiscussionName="
				+ groupDiscussionName + ", groupDiscussionBody=" + groupDiscussionBody + ", groupCreationTime="
				+ groupCreationTime + "]";
	}

}
