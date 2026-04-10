package com.ecowaste.ecowaste_tn.repository;

import com.ecowaste.ecowaste_tn.model.DisposalLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DisposalLocationRepository extends JpaRepository<DisposalLocation, Long> {
    Optional<DisposalLocation> findByCategory(String category);
}