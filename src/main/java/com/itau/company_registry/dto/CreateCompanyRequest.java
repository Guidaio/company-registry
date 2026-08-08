package com.itau.company_registry.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCompanyRequest {
    private String name;

    
    public String getName(){
        return name;
    }

    @NotBlank(message = "Company name cannot be blank") 
    public void setName(String name){
        this.name = name;
    }    
}
