package com.shivit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivit.entity.Complaint;
import com.shivit.entity.TicketHistory;

@Repository
public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {
	List<TicketHistory> findByComplaint(Complaint complaint);
}
