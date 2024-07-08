package com.shivit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "project_master", uniqueConstraints = @UniqueConstraint(columnNames = "project_name"))
@Data
public class ProjectMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project_name", nullable = false)
	private String projectName;

	@Column(name = "project_description", nullable = false)
	private String projectDescription;

	@Enumerated(EnumType.STRING)
	@Column(name = "project_status", nullable = false)
	private Status projectStatus;

	public enum Status {
		ACTIVE, INACTIVE
	}

}
