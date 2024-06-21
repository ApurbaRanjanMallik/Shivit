package com.shivit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "complaints")
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ticket_number", nullable = false, unique = true)
	private String ticketNumber;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@Column(name = "project_name", nullable = false)
	private String projectName;

	@Column(name = "module_name", nullable = false)
	private String moduleName;

	@Column(name = "sub_module_name", nullable = false)
	private String subModuleName;

	@Column(name = "frequency", nullable = false)
	private String frequency; // Always or Random

	@Column(name = "priority", nullable = false)
	private String priority; // Critical, High, Medium

	@Column(name = "issue_description", nullable = false, columnDefinition = "TEXT")
	private String issueDescription;

	@Column(name = "status", nullable = false)
	private String status; // New, Assigned, Resolved, Closed

	public Complaint() {
	}

	public Complaint(String ticketNumber, LocalDateTime date, String projectName, String moduleName,
			String subModuleName, String frequency, String priority, String issueDescription, String status) {
		this.ticketNumber = ticketNumber;
		this.date = date;
		this.projectName = projectName;
		this.moduleName = moduleName;
		this.subModuleName = subModuleName;
		this.frequency = frequency;
		this.priority = priority;
		this.issueDescription = issueDescription;
		this.status = status;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Complaint{" + "id=" + id + ", ticketNumber='" + ticketNumber + '\'' + ", date=" + date
				+ ", projectName='" + projectName + '\'' + ", moduleName='" + moduleName + '\'' + ", subModuleName='"
				+ subModuleName + '\'' + ", frequency='" + frequency + '\'' + ", priority='" + priority + '\''
				+ ", issueDescription='" + issueDescription + '\'' + ", status='" + status + '\'' + '}';
	}
}
