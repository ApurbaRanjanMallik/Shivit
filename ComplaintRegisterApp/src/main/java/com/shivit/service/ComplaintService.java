package com.shivit.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivit.entity.Complaint;
import com.shivit.entity.Complaint.Status;
import com.shivit.entity.TicketHistory;
import com.shivit.repo.ComplaintRepository;
import com.shivit.repo.TicketHistoryRepository;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private TicketHistoryRepository ticketHistoryRepository;

	public Complaint addComplaint(Complaint complaint) {
		complaint.setStatus(Status.NEW); 
		Complaint savedComplaint = complaintRepository.save(complaint);
		saveTicketHistory(savedComplaint, Status.NEW, "System");
		return savedComplaint;
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
		return null;
	}
}
