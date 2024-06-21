package com.shivit.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivit.dto.ComplaintDTO;
import com.shivit.entity.Complaint;
import com.shivit.service.ComplaintService;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;

	@PostMapping("/add")
	public ResponseEntity<?> addComplaint(@RequestBody ComplaintDTO complaintDTO) {
		Complaint complaint = mapToEntity(complaintDTO);
		Complaint savedComplaint = complaintService.addComplaint(complaint);
		return ResponseEntity.ok(savedComplaint);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Complaint>> getAllComplaints() {
		List<Complaint> complaints = complaintService.getAllComplaints();
		return ResponseEntity.ok(complaints);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id) {
		Complaint complaint = complaintService.getComplaintById(id);
		if (complaint != null) {
			return ResponseEntity.ok(complaint);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/ticket/{ticketNumber}")
	public ResponseEntity<Complaint> getComplaintByTicketNumber(@PathVariable String ticketNumber) {
		Complaint complaint = complaintService.getComplaintByTicketNumber(ticketNumber);
		if (complaint != null) {
			return ResponseEntity.ok(complaint);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/close/{id}")
	public ResponseEntity<Complaint> closeComplaint(@PathVariable Long id) {
		Complaint closedComplaint = complaintService.closeComplaint(id);
		if (closedComplaint != null) {
			return ResponseEntity.ok(closedComplaint);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private Complaint mapToEntity(ComplaintDTO complaintDTO) {
		Complaint complaint = new Complaint();
		BeanUtils.copyProperties(complaintDTO, complaint);
		complaint.setDate(LocalDateTime.now()); // Set current date/time

		return complaint;
	}

}
