package com.politicalforum.beans;

public class Notification {
	private int notificationId;
	private String notificationBody;
	private String notificationcreationTime;
	
	public Notification() {}

	public Notification(int notificationId, String notificationBody, String notificationcreationTime) {
		super();
		this.notificationId = notificationId;
		this.notificationBody = notificationBody;
		this.notificationcreationTime = notificationcreationTime;
	}

	public Notification(String notificationBody, String notificationcreationTime) {
		super();
		this.notificationBody = notificationBody;
		this.notificationcreationTime = notificationcreationTime;
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

	public String getNotificationcreationTime() {
		return notificationcreationTime;
	}

	public void setNotificationcreationTime(String notificationcreationTime) {
		this.notificationcreationTime = notificationcreationTime;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", notificationBody=" + notificationBody
				+ ", notificationcreationTime=" + notificationcreationTime + "]";
	}
	
}
