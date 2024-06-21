package com.shivit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivit.entity.Complaint;
import com.shivit.repo.ComplaintRepository;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;

	public Complaint addComplaint(Complaint complaint) {
		return complaintRepository.save(complaint);
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

	public Complaint closeComplaint(Long id) {
		Complaint complaint = complaintRepository.findById(id).orElse(null);
		if (complaint != null) {
			complaint.setStatus("Closed");
			return complaintRepository.save(complaint);
		}
		return null;
	}
}
