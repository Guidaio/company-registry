package com.itau.company_registry.service;

import com.itau.company_registry.model.Company;
import com.itau.company_registry.dto.CreateCompanyRequest;
import com.itau.company_registry.dto.CompanyResponse;
import com.itau.company_registry.repository.CompanyRepository;

import jakarta.transaction.Transactional;

import com.itau.company_registry.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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

    public CompanyResponse getCompanyById(Long id){
        Company company = companyRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o ID: " + id));

        return new CompanyResponse(company.getId(), company.getName());
    }

    public void deleteCompany(Long id){
        Company company = companyRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o ID: " + id));

        companyRepository.delete(company);
    }

    public void updateCompany(Long id, CreateCompanyRequest request){
        Company company = companyRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o ID: " + id));
        
        company.setName(request.getName());
    }

}
