package com.itau.company_registry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itau.company_registry.dto.CompanyResponse;
import com.itau.company_registry.dto.CreateCompanyRequest;
import com.itau.company_registry.model.Company;
import com.itau.company_registry.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping
    public CompanyResponse createCompany(@RequestBody CreateCompanyRequest request) {       
        return companyService.createCompany(request);
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }
}
