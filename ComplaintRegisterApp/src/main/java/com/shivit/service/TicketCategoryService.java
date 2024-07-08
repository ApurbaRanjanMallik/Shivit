package com.shivit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivit.entity.TicketCategory;
import com.shivit.repo.TicketCategoryRepository;

@Service
public class TicketCategoryService {

	@Autowired
	private TicketCategoryRepository ticketCategoryRepository;

	public List<TicketCategory> getAllTicketCategories() {
		return ticketCategoryRepository.findAll();
	}

	public List<TicketCategory> getOpenTicketCategories() {
		return ticketCategoryRepository.findAll().stream()
				.filter(ticketCategory -> ticketCategory.getTicketStatus() == TicketCategory.Status.OPEN).toList();
	}

	public TicketCategory getTicketCategoryById(Long id) {
		return ticketCategoryRepository.findById(id).orElse(null);
	}

	public TicketCategory addTicketCategory(TicketCategory ticketCategory) {
		return ticketCategoryRepository.save(ticketCategory);
	}

	public void deleteTicketCategory(Long id) {
		ticketCategoryRepository.deleteById(id);
	}

}
