package com.ecowaste.ecowaste_tn.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "waste_requests")
public class WasteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "citizen_name")
    private String citizenName;

    private String address;

    @Column(name = "waste_description")
    private String wasteDescription;

    private String category;

    private String status = "Pending";

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}