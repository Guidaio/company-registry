package com.itau.company_registry.repository;

import com.itau.company_registry.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company, Long> {
    
}
