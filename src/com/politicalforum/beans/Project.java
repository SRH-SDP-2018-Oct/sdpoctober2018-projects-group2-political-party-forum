package com.politicalforum.beans;

import java.sql.Date;

public class Project {
	private String groupProgressReportId;
	private String taskName;
	private Date taskStartDate;
	private Date taskEndDate;
	private Date intendedCompletionDate;
	private String taskDescription;
	private int taskAllocatedFund;
	private Date taskCreationTimestamp;
	private String contractorName;

	public Project() {
	}

	public Project(String groupProgressReportId, String taskName, Date taskStartDate, Date taskEndDate,
			Date intendedCompletionDate, String taskDescription, int taskAllocatedFund,
			Date taskCreationTimestamp, String contractorName) {
		super();
		this.groupProgressReportId = groupProgressReportId;
		this.taskName = taskName;
		this.taskStartDate = taskStartDate;
		this.taskEndDate = taskEndDate;
		this.intendedCompletionDate = intendedCompletionDate;
		this.taskDescription = taskDescription;
		this.taskAllocatedFund = taskAllocatedFund;
		this.taskCreationTimestamp = taskCreationTimestamp;
		this.contractorName = contractorName;
	}

	public Project(String taskName, Date taskStartDate, Date taskEndDate, Date intendedCompletionDate,
			String taskDescription, int taskAllocatedFund, Date taskCreationTimestamp, String contractorName) {
		super();
		this.taskName = taskName;
		this.taskStartDate = taskStartDate;
		this.taskEndDate = taskEndDate;
		this.intendedCompletionDate = intendedCompletionDate;
		this.taskDescription = taskDescription;
		this.taskAllocatedFund = taskAllocatedFund;
		this.taskCreationTimestamp = taskCreationTimestamp;
		this.contractorName = contractorName;
	}

	public String getGroupProgressReportId() {
		return groupProgressReportId;
	}

	public void setGroupProgressReportId(String groupProgressReportId) {
		this.groupProgressReportId = groupProgressReportId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public Date getIntendedCompletionDate() {
		return intendedCompletionDate;
	}

	public void setIntendedCompletionDate(Date intendedCompletionDate) {
		this.intendedCompletionDate = intendedCompletionDate;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public int getTaskAllocatedFund() {
		return taskAllocatedFund;
	}

	public void setTaskAllocatedFund(int taskAllocatedFund) {
		this.taskAllocatedFund = taskAllocatedFund;
	}

	public Date getTaskCreationTimestamp() {
		return taskCreationTimestamp;
	}

	public void setTaskCreationTimestamp(Date taskCreationTimestamp) {
		this.taskCreationTimestamp = taskCreationTimestamp;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	@Override
	public String toString() {
		return "Project [groupProgressReportId=" + groupProgressReportId + ", taskName=" + taskName + ", taskStartDate="
				+ taskStartDate + ", taskEndDate=" + taskEndDate + ", intendedCompletionDate=" + intendedCompletionDate
				+ ", taskDescription=" + taskDescription + ", taskAllocatedFund=" + taskAllocatedFund
				+ ", taskCreationTimestamp=" + taskCreationTimestamp + ", contractorName=" + contractorName + "]";
	}

}
