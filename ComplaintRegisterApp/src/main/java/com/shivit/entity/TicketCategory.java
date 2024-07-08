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
@Table(name = "ticket_category", uniqueConstraints = @UniqueConstraint(columnNames = "ticket_name"))
@Data
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_name", nullable = false)
    private String ticketName;

    @Column(name = "ticket_description", nullable = false)
    private String ticketDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status", nullable = false)
    private Status ticketStatus;

    public enum Status {
        OPEN, CLOSED
    }
}
