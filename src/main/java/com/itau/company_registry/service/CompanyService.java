package com.itau.company_registry.service;

import com.itau.company_registry.model.Company;
import com.itau.company_registry.dto.CreateCompanyRequest;
import com.itau.company_registry.dto.CompanyResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CompanyService {

    private final List<Company> companies = new ArrayList<>();

    public CompanyResponse createCompany(CreateCompanyRequest request){
        Company company = new Company();
        company.setId((long) (companies.size() + 1));
        company.setName(request.getName());
        companies.add(company);
        
        return new CompanyResponse(company.getId(), company.getName());
    }

    public List<Company> getCompanies(){
        return companies;
    }

}
