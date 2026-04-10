package com.ecowaste.ecowaste_tn.repository;

import com.ecowaste.ecowaste_tn.model.WasteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteRequestRepository extends JpaRepository<WasteRequest, Long> {
}