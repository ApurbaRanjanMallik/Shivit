package com.shivit.dto;

public class ComplaintDTO {

	private String projectName;
	private String moduleName;
	private String subModuleName;
	private String frequency;
	private String priority;
	private String issueDescription;

	public ComplaintDTO() {
	}

	public ComplaintDTO(String projectName, String moduleName, String subModuleName, String frequency, String priority,
			String issueDescription) {
		this.projectName = projectName;
		this.moduleName = moduleName;
		this.subModuleName = subModuleName;
		this.frequency = frequency;
		this.priority = priority;
		this.issueDescription = issueDescription;
	}

	// Getters and Setters

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	@Override
	public String toString() {
		return "ComplaintDTO{" + "projectName='" + projectName + '\'' + ", moduleName='" + moduleName + '\''
				+ ", subModuleName='" + subModuleName + '\'' + ", frequency='" + frequency + '\'' + ", priority='"
				+ priority + '\'' + ", issueDescription='" + issueDescription + '\'' + '}';
	}
}
