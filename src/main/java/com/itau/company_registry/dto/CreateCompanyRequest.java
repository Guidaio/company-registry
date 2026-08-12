package com.itau.company_registry.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateCompanyRequest(@NotBlank(message = "Company name cannot be blank") String name) { }    
