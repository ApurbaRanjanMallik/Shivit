package com.shivit.dto;

import com.shivit.entity.Complaint.Status;

import lombok.Data;
	
@Data
public class ComplaintDTO {
	private String ticketNumber;
	private String projectName;
	private String moduleName;
	private String subModuleName;
	private String frequency;
	private String priority;
	private String issueDescription;
	private Status status;

}
