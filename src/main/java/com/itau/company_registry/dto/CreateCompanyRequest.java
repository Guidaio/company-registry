package com.itau.company_registry.dto;

public class CreateCompanyRequest {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }    
}
