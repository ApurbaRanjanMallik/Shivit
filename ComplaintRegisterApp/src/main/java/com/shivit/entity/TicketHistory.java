package com.shivit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ticket_history")
@Data
public class TicketHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Complaint complaint;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Complaint.Status status;

	@Column(name = "updated_by", nullable = false)
	private String updatedBy;

	@Column(name = "update_time", nullable = false)
	private LocalDateTime updateTime;
}
