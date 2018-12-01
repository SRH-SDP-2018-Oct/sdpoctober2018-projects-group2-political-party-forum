package com.politicalforum.beans;

public class Poll {
	private int pollId;
	private String pollName;
	private String pollOption;
	private String pollAnswer;
	private String pollCreationTime;
	
	public Poll() {}

	public Poll(int pollId, String pollName, String pollOption, String pollAnswer, String pollCreationTime) {
		super();
		this.pollId = pollId;
		this.pollName = pollName;
		this.pollOption = pollOption;
		this.pollAnswer = pollAnswer;
		this.pollCreationTime = pollCreationTime;
	}

	public Poll(String pollName, String pollOption, String pollAnswer, String pollCreationTime) {
		super();
		this.pollName = pollName;
		this.pollOption = pollOption;
		this.pollAnswer = pollAnswer;
		this.pollCreationTime = pollCreationTime;
	}

	public int getPollId() {
		return pollId;
	}

	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	public String getPollName() {
		return pollName;
	}

	public void setPollName(String pollName) {
		this.pollName = pollName;
	}

	public String getPollOption() {
		return pollOption;
	}

	public void setPollOption(String pollOption) {
		this.pollOption = pollOption;
	}

	public String getPollAnswer() {
		return pollAnswer;
	}

	public void setPollAnswer(String pollAnswer) {
		this.pollAnswer = pollAnswer;
	}

	public String getPollCreationTime() {
		return pollCreationTime;
	}

	public void setPollCreationTime(String pollCreationTime) {
		this.pollCreationTime = pollCreationTime;
	}

	@Override
	public String toString() {
		return "Poll [pollId=" + pollId + ", pollName=" + pollName + ", pollOption=" + pollOption + ", pollAnswer="
				+ pollAnswer + ", pollCreationTime=" + pollCreationTime + "]";
	}
	
}
