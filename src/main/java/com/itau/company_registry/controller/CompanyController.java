package com.itau.company_registry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itau.company_registry.dto.CompanyResponse;
import com.itau.company_registry.dto.CreateCompanyRequest;
import com.itau.company_registry.model.Company;
import com.itau.company_registry.service.CompanyService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/companies")
@Tag(name = "Companies", description = "Endpoints para gerenciamento de empresas")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping
    @Tag(name = "Criar empresa", description = "Endpoint para criar uma nova empresa")
    public CompanyResponse createCompany(@Valid @RequestBody CreateCompanyRequest request) {       
        return companyService.createCompany(request);
    }

    @GetMapping
    @Tag(name = "Buscar empresas", description = "Endpoint para retornar uma lista de empresas")
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    @Tag(name = "Buscar empresa por ID", description = "Endpoint para buscar uma empresa por ID")
    public CompanyResponse getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }
    

    @PutMapping("/{id}")
    @Tag(name = "Alterar empresa", description = "Endpoint para alterar empresa pelo id")
    public ResponseEntity<Void> updateCompany(@PathVariable Long id, @Valid @RequestBody CreateCompanyRequest request) {
        companyService.updateCompany(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Deletar empresa", description = "Endpoint para deletar empresa pelo id")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
