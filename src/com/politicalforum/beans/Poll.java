package com.politicalforum.beans;

import java.sql.Date;

public class Poll {

	private String pollId;
	private String pollTopic;
	private Date dateOfPoll ;
	private String option1;
	private String option2;
	private String option3;
	private String userId;
	private String groupDetailsId;
	private String groupFollowersId;
	
	public Poll(String pollId, String pollTopic, Date dateOfPoll, String option1, String option2, String option3,
			String userId, String groupDetailsId) {
		super();
		this.pollId = pollId;
		this.pollTopic = pollTopic;
		this.dateOfPoll = dateOfPoll;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.userId = userId;
		this.groupDetailsId = groupDetailsId;
	}
	public Poll(String pollTopic, Date dateOfPoll, String option1, String option2, String option3, String userId,
			String groupDetailsId) {
		super();
		this.pollTopic = pollTopic;
		this.dateOfPoll = dateOfPoll;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.userId = userId;
		this.groupDetailsId = groupDetailsId;
	}
	public String getPollId() {
		return pollId;
	}
	public void setPollId(String pollId) {
		this.pollId = pollId;
	}
	public String getPollTopic() {
		return pollTopic;
	}
	public void setPollTopic(String pollTopic) {
		this.pollTopic = pollTopic;
	}
	public Date getDateOfPoll() {
		return dateOfPoll;
	}
	public void setDateOfPoll(Date dateOfPoll) {
		this.dateOfPoll = dateOfPoll;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupDetailsId() {
		return this.groupDetailsId;
	}
	public void setGroupDetailsId(String groupDetailsId) {
		this.groupDetailsId = groupDetailsId;
	}
	public String getGroupFollowersId() {
		return this.groupFollowersId;
	}
	public void setGroupFollowersId(String groupFollowersId) {
		this.groupFollowersId = groupFollowersId;
	}
	@Override
	public String toString() {
		return "Poll [pollId=" + pollId + ", pollTopic=" + pollTopic + ", dateOfPoll=" + dateOfPoll + ", option1="
				+ option1 + ", option2=" + option2 + ", option3=" + option3 + ", userId=" + userId + ", GroupDetailsId="
				+ groupDetailsId + ", GroupFollowersId=" + groupFollowersId + "]";
	}

	
	
}

	