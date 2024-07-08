package com.shivit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivit.entity.TicketCategory;

public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {

	
}
