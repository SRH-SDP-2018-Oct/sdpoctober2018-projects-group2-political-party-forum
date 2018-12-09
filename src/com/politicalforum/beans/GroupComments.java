package com.politicalforum.beans;

import java.util.Date;

public class GroupComments {
	private String commentId;
	private String commentBody;
	private Date commentCreationTime;
	private String commentPostedBy;
	
	public GroupComments(String commentId, String commentBody, Date commentCreationTime, String commentPostedBy) {
		super();
		this.commentId = commentId;
		this.commentBody = commentBody;
		this.commentCreationTime = commentCreationTime;
		this.commentPostedBy = commentPostedBy;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public Date getCommentCreationTime() {
		return commentCreationTime;
	}

	public void setCommentCreationTime(Date commentCreationTime) {
		this.commentCreationTime = commentCreationTime;
	}

	public String getCommentPostedBy() {
		return commentPostedBy;
	}

	public void setCommentPostedBy(String commentPostedBy) {
		this.commentPostedBy = commentPostedBy;
	}

	@Override
	public String toString() {
		return "GroupComments [commentId=" + commentId + ", commentBody=" + commentBody + ", commentCreationTime="
				+ commentCreationTime + ", commentPostedBy=" + commentPostedBy + "]";
	}

}
