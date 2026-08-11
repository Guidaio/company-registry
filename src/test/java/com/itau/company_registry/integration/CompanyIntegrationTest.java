package com.itau.company_registry.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.itau.company_registry.dto.CreateCompanyRequest;
import com.itau.company_registry.exception.ErrorResponse;
import com.itau.company_registry.dto.CompanyResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateCompany() {
        CreateCompanyRequest request = new CreateCompanyRequest();

        request.setName("Itaú Unibanco");
        ResponseEntity<CompanyResponse> response = restTemplate.postForEntity("/companies", request, CompanyResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Itaú Unibanco", response.getBody().getName());
        assertNotNull(response.getBody().getId());
    }

    @Test
    void shouldUpdateCompany(){
        CreateCompanyRequest request = new CreateCompanyRequest();
        request.setName("Itaú Trocar");
        ResponseEntity<CompanyResponse> response = restTemplate.postForEntity("/companies", request, CompanyResponse.class);
        Long Id = response.getBody().getId();

        request.setName("Itaú Atualizado");
        restTemplate.put("/companies/" + Id, request);
        ResponseEntity<CompanyResponse> getResponse = restTemplate.getForEntity("/companies/", CompanyResponse.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Itaú Atualizado", getResponse.getBody().getName());        
    }

    @Test
    void shouldDeleteCompany(){
        CreateCompanyRequest request = new CreateCompanyRequest();
        request.setName("Itaú Deletar");
        ResponseEntity<CompanyResponse> response = restTemplate.postForEntity("/companies/", request, CompanyResponse.class);
        Long id = response.getBody().getId();

        restTemplate.delete("/companies/" + id);

        ResponseEntity<ErrorResponse> getResponse = restTemplate.getForEntity("/companies/", ErrorResponse.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
    
}
