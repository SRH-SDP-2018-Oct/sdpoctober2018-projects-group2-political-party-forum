package com.politicalforum.beans;

import java.sql.Date;

public class Notification {
	private String notificationId;
	private String notificationBody;
	private Date notificationCreationTime;

	public Notification() {
	}

	public Notification(String notificationId, String notificationBody, Date notificationCreationTime) {
		super();
		this.notificationId = notificationId;
		this.notificationBody = notificationBody;
		this.notificationCreationTime = notificationCreationTime;
	}

	public Notification(String notificationBody, Date notificationCreationTime) {
		super();
		this.notificationBody = notificationBody;
		this.notificationCreationTime = notificationCreationTime;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationBody() {
		return notificationBody;
	}

	public void setNotificationBody(String notificationBody) {
		this.notificationBody = notificationBody;
	}

	public Date getNotificationCreationTime() {
		return notificationCreationTime;
	}

	public void setNotificationCreationTime(Date notificationCreationTime) {
		this.notificationCreationTime = notificationCreationTime;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", notificationBody=" + notificationBody
				+ ", notificationCreationTime=" + notificationCreationTime + "]";
	}

}
