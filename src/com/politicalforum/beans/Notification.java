package com.politicalforum.beans;

public class Notification {
	private int notificationId;
	private String notificationBody;
	private String notificationCreationTime;

	public Notification() {
	}

	public Notification(int notificationId, String notificationBody, String notificationCreationTime) {
		super();
		this.notificationId = notificationId;
		this.notificationBody = notificationBody;
		this.notificationCreationTime = notificationCreationTime;
	}

	public Notification(String notificationBody, String notificationCreationTime) {
		super();
		this.notificationBody = notificationBody;
		this.notificationCreationTime = notificationCreationTime;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationBody() {
		return notificationBody;
	}

	public void setNotificationBody(String notificationBody) {
		this.notificationBody = notificationBody;
	}

	public String getNotificationCreationTime() {
		return notificationCreationTime;
	}

	public void setNotificationCreationTime(String notificationCreationTime) {
		this.notificationCreationTime = notificationCreationTime;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", notificationBody=" + notificationBody
				+ ", notificationCreationTime=" + notificationCreationTime + "]";
	}

}
