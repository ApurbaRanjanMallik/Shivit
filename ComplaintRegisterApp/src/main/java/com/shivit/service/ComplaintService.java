package com.shivit.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.shivit.entity.Complaint;
import com.shivit.entity.Complaint.Status;
import com.shivit.entity.TicketHistory;
import com.shivit.repo.ComplaintRepository;
import com.shivit.repo.TicketHistoryRepository;
import com.shivit.util.ComplaintSpecifications;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private TicketHistoryRepository ticketHistoryRepository;

	public Complaint addComplaint(Complaint complaint) {
		complaint.setStatus(Status.NEW);
		complaint.setTicketNumber(generateTicketNumber());
		Complaint savedComplaint = complaintRepository.save(complaint);
		saveTicketHistory(savedComplaint, Status.NEW, "System");
		return savedComplaint;
	}

	public String generateTicketNumber() {
		long count = complaintRepository.count();
		return "eTicketNo-" + (count + 1);
	}

	public List<Complaint> getAllComplaints() {
		return complaintRepository.findAll();
	}

	public Complaint getComplaintById(Long id) {
		return complaintRepository.findById(id).orElse(null);
	}

	public Complaint getComplaintByTicketNumber(String ticketNumber) {
		return complaintRepository.findByTicketNumber(ticketNumber);
	}

	public Complaint updateComplaintStatus(Long id, Status status, String updatedBy) {
		Complaint complaint = complaintRepository.findById(id).orElse(null);
		if (complaint != null) {
			complaint.setStatus(status);
			complaintRepository.save(complaint);
			saveTicketHistory(complaint, status, updatedBy);
			return complaint;
		}
		return null;
	}

	public Complaint closeComplaint(Long id, String updatedBy) {
		return updateComplaintStatus(id, Status.CLOSED, updatedBy);
	}

	private void saveTicketHistory(Complaint complaint, Status status, String updatedBy) {
		TicketHistory history = new TicketHistory();
		history.setComplaint(complaint);
		history.setStatus(status);
		history.setUpdatedBy(updatedBy);
		history.setUpdateTime(LocalDateTime.now());
		ticketHistoryRepository.save(history);
	}

	public List<TicketHistory> getComplaintHistory(Long complaintId) {
		Complaint complaint = getComplaintById(complaintId);
		if (complaint != null) {
			return ticketHistoryRepository.findByComplaint(complaint);
		}
		return Collections.emptyList();
	}
	
	public List<Complaint> filterComplaints(String status, LocalDate startDate, LocalDate endDate, Long projectId, Long ticketCategoryId) {
        // Add your filtering logic here
        Specification<Complaint> spec = Specification.where(null);

        if (status != null && !status.isEmpty()) {
            spec = spec.and(ComplaintSpecifications.hasStatus(status));
        }

        if (startDate != null) {
            spec = spec.and(ComplaintSpecifications.hasDateAfter(startDate.atStartOfDay()));
        }

        if (endDate != null) {
            spec = spec.and(ComplaintSpecifications.hasDateBefore(endDate.atTime(LocalTime.MAX)));
        }

        if (projectId != null) {
            spec = spec.and(ComplaintSpecifications.hasProjectId(projectId));
        }

        if (ticketCategoryId != null) {
            spec = spec.and(ComplaintSpecifications.hasTicketCategoryId(ticketCategoryId));
        }

        return complaintRepository.findAll(spec);
    }

}
