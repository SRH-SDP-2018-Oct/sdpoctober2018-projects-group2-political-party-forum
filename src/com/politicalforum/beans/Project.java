package com.politicalforum.beans;

public class Project {
	private int groupProgressReportId;
	private String taskName;
	private String taskStartDate;
	private String taskEndDate;
	private String intendedCompletionDate;
	private String taskDescription;
	private double taskAllocatedFund;
	private String taskCreationTimestamp;
	private String contractorName;

	public Project() {
	}

	public Project(int groupProgressReportId, String taskName, String taskStartDate, String taskEndDate,
			String intendedCompletionDate, String taskDescription, double taskAllocatedFund,
			String taskCreationTimestamp, String contractorName) {
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

	public Project(String taskName, String taskStartDate, String taskEndDate, String intendedCompletionDate,
			String taskDescription, double taskAllocatedFund, String taskCreationTimestamp, String contractorName) {
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

	public int getGroupProgressReportId() {
		return groupProgressReportId;
	}

	public void setGroupProgressReportId(int groupProgressReportId) {
		this.groupProgressReportId = groupProgressReportId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public String getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public String getIntendedCompletionDate() {
		return intendedCompletionDate;
	}

	public void setIntendedCompletionDate(String intendedCompletionDate) {
		this.intendedCompletionDate = intendedCompletionDate;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public double getTaskAllocatedFund() {
		return taskAllocatedFund;
	}

	public void setTaskAllocatedFund(double taskAllocatedFund) {
		this.taskAllocatedFund = taskAllocatedFund;
	}

	public String getTaskCreationTimestamp() {
		return taskCreationTimestamp;
	}

	public void setTaskCreationTimestamp(String taskCreationTimestamp) {
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
