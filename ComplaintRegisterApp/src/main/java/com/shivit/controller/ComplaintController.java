package com.shivit.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shivit.dto.ComplaintDTO;
import com.shivit.entity.Complaint;
import com.shivit.entity.Complaint.Status;
import com.shivit.entity.ProjectMaster;
import com.shivit.entity.TicketCategory;
import com.shivit.entity.TicketHistory;
import com.shivit.service.ComplaintService;
import com.shivit.service.ProjectMasterService;
import com.shivit.service.TicketCategoryService;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;

	@Autowired
	private ProjectMasterService projectMasterService;

	@Autowired
	private TicketCategoryService ticketCategoryService;

	@GetMapping("/app")
	public ModelAndView showAppFunctionalities(ModelMap model) {
		return new ModelAndView("viewApp", model);
	}

	@GetMapping("/registerform")
	public ModelAndView showComplaintForm(ModelMap model) {
		model.addAttribute("complaint", new ComplaintDTO());
		model.addAttribute("projects", projectMasterService.getActiveProjects());
		model.addAttribute("ticketCategories", ticketCategoryService.getOpenTicketCategories());
		return new ModelAndView("addComplaint", model);
	}

	@PostMapping("/add")
	public ModelAndView addComplaint(@ModelAttribute ComplaintDTO complaintDTO,
			@RequestParam("attachment") MultipartFile imageFile, ModelMap model) {

		Complaint complaint = mapToEntity(complaintDTO);
		complaint.setProject(projectMasterService.getProjectById(complaintDTO.getProjectId()));
		complaint.setTicketCategory(ticketCategoryService.getTicketCategoryById(complaintDTO.getTicketCategoryId()));

		// Handle image file upload
		if (imageFile != null && !imageFile.isEmpty()) {
			try {
				complaint.setImage(imageFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("error", "Failed to upload image");
				return new ModelAndView("addComplaint", model);
			}
		}
		// Save the complaint
		complaint = complaintService.addComplaint(complaint);
		model.addAttribute("complaint", complaint);
		return new ModelAndView("redirect:/complaints/list", model);
	}

	@GetMapping("/list")
	public ModelAndView getAllComplaints(ModelMap model) {
		List<Complaint> complaints = complaintService.getAllComplaints();
		model.addAttribute("complaints", complaints);
		return new ModelAndView("listComplaints", model);
	}

	@GetMapping("/{id}")
	public ModelAndView getComplaintById(@PathVariable Long id, ModelMap model) {
		Complaint complaint = complaintService.getComplaintById(id);
		if (complaint != null) {
			model.addAttribute("complaint", complaint);
			if (complaint.getImage() != null) {
				// Convert byte[] image data to Base64 string
				String imageAsBase64 = Base64.getEncoder().encodeToString(complaint.getImage());
				model.addAttribute("imageAsBase64", imageAsBase64);
			}
			return new ModelAndView("viewComplaint", model);
		} else {
			// Handle case where complaint with given ID is not found
			return new ModelAndView("redirect:/complaints/list", model);
		}
	}

	@GetMapping("/ticket/{ticketNumber}")
	public ModelAndView getComplaintByTicketNumber(@PathVariable String ticketNumber, ModelMap model) {
		Complaint complaint = complaintService.getComplaintByTicketNumber(ticketNumber);
		model.addAttribute("complaint", complaint);
		return new ModelAndView("viewComplaint", model);
	}

	@GetMapping("/closeform/{id}")
	public ModelAndView showCloseComplaintForm(@PathVariable Long id, ModelMap model) {
		Complaint complaint = complaintService.getComplaintById(id);
		if (complaint != null) {
			model.addAttribute("complaint", complaint);
			return new ModelAndView("closeComplaint", model);
		} else {
			return new ModelAndView("redirect:/complaints/list", model);
		}
	}

	@PostMapping("/close/{id}")
	public ModelAndView closeComplaint(@PathVariable Long id, @RequestParam String updatedBy) {
		Complaint closedComplaint = complaintService.closeComplaint(id, updatedBy);
		if (closedComplaint != null) {
			return new ModelAndView("redirect:/complaints/list");
		}
		return new ModelAndView("redirect:/complaints/list");
	}

	@GetMapping("/update-statusform/{id}")
	public ModelAndView showUpdateStatusForm(@PathVariable Long id, ModelMap model) {
		Complaint complaint = complaintService.getComplaintById(id);
		if (complaint != null) {
			model.addAttribute("complaint", complaint);
			model.addAttribute("statuses", Complaint.Status.values());
			return new ModelAndView("updateStatus", model);
		} else {
			return new ModelAndView("redirect:/complaints/list", model);
		}
	}

	@PostMapping("/update-status/{id}")
	public ModelAndView updateComplaintStatus(@PathVariable Long id, @RequestParam Status status,
			@RequestParam String updatedBy) {
		Complaint updatedComplaint = complaintService.updateComplaintStatus(id, status, updatedBy);
		if (updatedComplaint != null) {
			return new ModelAndView("redirect:/complaints/list");
		}
		return new ModelAndView("redirect:/complaints/list");
	}

	@GetMapping("/history/{id}")
	public ModelAndView getComplaintHistory(@PathVariable Long id, ModelMap model) {
		List<TicketHistory> history = complaintService.getComplaintHistory(id);
		model.addAttribute("history", history);
		return new ModelAndView("viewComplaintHistory", model);
	}

	@GetMapping("/report")
	public ModelAndView getComplaintReport(
	        @RequestParam(value = "status", required = false) String status,
	        @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	        @RequestParam(value = "projectId", required = false) Long projectId,
	        @RequestParam(value = "ticketCategoryId", required = false) Long ticketCategoryId,
	        ModelMap model) {

	    List<Complaint> complaints = complaintService.filterComplaints(status, startDate, endDate, projectId, ticketCategoryId);
	    List<ProjectMaster> projects = projectMasterService.getAllProjects();
	    List<TicketCategory> ticketCategories = ticketCategoryService.getAllTicketCategories();

	    model.addAttribute("complaints", complaints);
	    model.addAttribute("projects", projects);
	    model.addAttribute("ticketCategories", ticketCategories);

	    return new ModelAndView("viewComplaintReport", model);
	}


	private Complaint mapToEntity(ComplaintDTO complaintDTO) {
		Complaint complaint = new Complaint();
		BeanUtils.copyProperties(complaintDTO, complaint);
		complaint.setDate(LocalDateTime.now());
		return complaint;
	}

}
