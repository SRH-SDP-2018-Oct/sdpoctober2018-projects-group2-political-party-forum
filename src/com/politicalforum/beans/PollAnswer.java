package com.politicalforum.beans;

public class PollAnswer {

	private String pollAnswerId;
	private String pollId;
	private String userId;
	private String answer;
	
	public PollAnswer(String pollAnswerId, String pollId, String userId, String answer) {
		super();
		this.pollAnswerId = pollAnswerId;
		this.pollId = pollId;
		this.userId = userId;
		this.answer = answer;
	}

	public PollAnswer(String pollId, String userId, String answer) {
		super();
		this.pollId = pollId;
		this.userId = userId;
		this.answer = answer;
	}

	public String getPollAnswerId() {
		return pollAnswerId;
	}

	public void setPollAnswerId(String pollAnswerId) {
		this.pollAnswerId = pollAnswerId;
	}

	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
	
}
