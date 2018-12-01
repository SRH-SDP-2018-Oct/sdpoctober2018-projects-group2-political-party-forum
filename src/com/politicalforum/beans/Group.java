package com.politicalforum.beans;

public class Group {

	private int groupId ;
	private String groupName;
	private String groupDescription;
	private int groupOwnerId;
	private String groupCreationTime;
	
	public Group() {}
	
	public Group(int groupId, String groupName, String groupDescription, int groupOwnerId, String groupCreationTime) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.groupOwnerId = groupOwnerId;
		this.groupCreationTime = groupCreationTime;
	}

	public Group(String groupName, String groupDescription, int groupOwnerId, String groupCreationTime) {
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

	public int getGroupOwnerId() {
		return groupOwnerId;
	}

	public void setGroupOwnerId(int groupOwnerId) {
		this.groupOwnerId = groupOwnerId;
	}

	public String getGroupCreationTime() {
		return groupCreationTime;
	}

	public void setGroupCreationTime(String groupCreationTime) {
		this.groupCreationTime = groupCreationTime;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", groupDescription=" + groupDescription
				+ ", groupOwnerId=" + groupOwnerId + ", groupCreationTime=" + groupCreationTime + "]";
	}
	
	
}
