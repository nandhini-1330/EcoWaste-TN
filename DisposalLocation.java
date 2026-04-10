package com.ecowaste.ecowaste_tn.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "disposal_locations")
public class DisposalLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @Column(name = "location_name")
    private String locationName;

    private String address;
    private String timings;
    private String contact;
}