package com.shivit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "complaints")
@Data
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ticket_number", nullable = false, unique = true)
	private String ticketNumber;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private ProjectMaster project;

	@ManyToOne
	@JoinColumn(name = "ticket_id", referencedColumnName = "id")
	private TicketCategory ticketCategory;

	@Column(name = "module_name", nullable = false)
	private String moduleName;

	
	@Column(name = "sub_module_name", nullable = false)
	private String subModuleName;

	@Column(name = "frequency", nullable = false)
	private String frequency;

	@Column(name = "priority", nullable = false)
	private String priority;

	@Column(name = "issue_description", nullable = false, columnDefinition = "TEXT")
	private String issueDescription;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@Lob
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private byte[] image;

	public enum Status {
		NEW, ASSIGNED, RESOLVED, CLOSED
	}
}