package com.shivit.dto;

import org.springframework.web.multipart.MultipartFile;

import com.shivit.entity.Complaint.Status;

import lombok.Data;

@Data
public class ComplaintDTO {

	private String ticketNumber;
	private Long projectId;
	private Long ticketCategoryId;
	private String moduleName;
	private String subModuleName;
	private String frequency;
	private String priority;
	private String issueDescription;
	private Status status;
	private MultipartFile imageFile;
}
