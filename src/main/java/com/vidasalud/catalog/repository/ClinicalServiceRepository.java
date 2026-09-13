package com.vidasalud.catalog.repository;

import com.vidasalud.catalog.model.ClinicalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalServiceRepository extends JpaRepository<ClinicalService, Long> {
}
