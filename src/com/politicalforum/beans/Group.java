package com.politicalforum.beans;

import java.sql.Date;

public class Group {

	private String groupId;
	private String groupName;
	private String groupDescription;
	private String groupOwnerId;
	private Date groupCreationTime;

	public Group() {
	}

	public Group(String groupName) {
		super();
		this.groupName = groupName;
	}
	
	public Group(String groupId, String groupName, String groupDescription, String groupOwnerId, Date groupCreationTime) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.groupOwnerId = groupOwnerId;
		this.groupCreationTime = groupCreationTime;
	}

	public Group(String groupName, String groupDescription, String groupOwnerId, Date groupCreationTime) {
		super();
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.groupOwnerId = groupOwnerId;
		this.groupCreationTime = groupCreationTime;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getGroupOwnerId() {
		return groupOwnerId;
	}

	public void setGroupOwnerId(String groupOwnerId) {
		this.groupOwnerId = groupOwnerId;
	}

	public Date getGroupCreationTime() {
		return groupCreationTime;
	}

	public void setGroupCreationTime(Date groupCreationTime) {
		this.groupCreationTime = groupCreationTime;
	}

	public String getGroupId() {
		return this.groupId;
	}
	
	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", groupDescription=" + groupDescription
				+ ", groupOwnerId=" + groupOwnerId + ", groupCreationTime=" + groupCreationTime + "]";
	}

}
