package com.politicalforum.beans;

public class GroupComments {
	private int commentId;
	private String commentBody;
	private String commentCreationTime;
	
	public GroupComments() {}

	public GroupComments(int commentId, String commentBody, String commentCreationTime) {
		super();
		this.commentId = commentId;
		this.commentBody = commentBody;
		this.commentCreationTime = commentCreationTime;
	}

	public GroupComments(String commentBody, String commentCreationTime) {
		super();
		this.commentBody = commentBody;
		this.commentCreationTime = commentCreationTime;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public String getCommentCreationTime() {
		return commentCreationTime;
	}

	public void setCommentCreationTime(String commentCreationTime) {
		this.commentCreationTime = commentCreationTime;
	}

	@Override
	public String toString() {
		return "GroupComments [commentId=" + commentId + ", commentBody=" + commentBody + ", commentCreationTime="
				+ commentCreationTime + "]";
	}
	
}
