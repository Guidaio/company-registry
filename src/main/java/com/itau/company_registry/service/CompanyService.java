package com.itau.company_registry.service;

import com.itau.company_registry.model.Company;
import com.itau.company_registry.dto.CreateCompanyRequest;
import com.itau.company_registry.dto.CompanyResponse;
import com.itau.company_registry.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponse createCompany(CreateCompanyRequest request){
        Company company = new Company();
        company.setName(request.getName());
    
        Company savedCompany = companyRepository.save(company);

        return new CompanyResponse(savedCompany.getId(), savedCompany.getName());
    }

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

}
