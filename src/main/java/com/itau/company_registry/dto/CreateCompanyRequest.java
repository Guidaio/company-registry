package com.itau.company_registry.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCompanyRequest {

    @NotBlank(message = "Company name cannot be blank") 
    private String name;
    
    public String getName(){
        return name;
    }   
    
    public void setName(String name){
        this.name = name;
    }    
}
