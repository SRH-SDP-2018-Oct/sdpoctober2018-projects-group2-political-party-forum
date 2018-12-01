package com.politicalforum.beans;

public class GroupDiscussion {
	private int groupDiscussionId;
	private String groupDiscussionName;
	private String groupDiscussionBody;
	private String groupCreationTime;
	
	public GroupDiscussion() {}

	public GroupDiscussion(int groupDiscussionId, String groupDiscussionName, String groupDiscussionBody,
			String groupCreationTime) {
		super();
		this.groupDiscussionId = groupDiscussionId;
		this.groupDiscussionName = groupDiscussionName;
		this.groupDiscussionBody = groupDiscussionBody;
		this.groupCreationTime = groupCreationTime;
	}

	public GroupDiscussion(String groupDiscussionName, String groupDiscussionBody, String groupCreationTime) {
		super();
		this.groupDiscussionName = groupDiscussionName;
		this.groupDiscussionBody = groupDiscussionBody;
		this.groupCreationTime = groupCreationTime;
	}

	public int getGroupDiscussionId() {
		return groupDiscussionId;
	}

	public void setGroupDiscussionId(int groupDiscussionId) {
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

	public String getGroupCreationTime() {
		return groupCreationTime;
	}

	public void setGroupCreationTime(String groupCreationTime) {
		this.groupCreationTime = groupCreationTime;
	}

	@Override
	public String toString() {
		return "GroupDiscussion [groupDiscussionId=" + groupDiscussionId + ", groupDiscussionName="
				+ groupDiscussionName + ", groupDiscussionBody=" + groupDiscussionBody + ", groupCreationTime="
				+ groupCreationTime + "]";
	}
	
	
}
