package com.shivit.util;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.shivit.entity.Complaint;
import com.shivit.entity.Complaint.Status;

public class ComplaintSpecifications {

	public static Specification<Complaint> hasStatus(String status) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), Status.valueOf(status));
	}

	public static Specification<Complaint> hasDateAfter(LocalDateTime date) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("date"), date);
	}

	public static Specification<Complaint> hasDateBefore(LocalDateTime date) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("date"), date);
	}

	public static Specification<Complaint> hasProjectId(Long projectId) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("project").get("id"), projectId);
	}

	public static Specification<Complaint> hasTicketCategoryId(Long ticketCategoryId) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ticketCategory").get("id"),
				ticketCategoryId);
	}
}
