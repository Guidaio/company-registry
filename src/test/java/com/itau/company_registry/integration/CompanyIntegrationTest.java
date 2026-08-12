package com.itau.company_registry.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
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
    private TestRestTemplate authenticatedRestTemplate;


    @BeforeEach
    void setUp() {
        this.authenticatedRestTemplate = restTemplate.withBasicAuth("admin", "admin123");
    }

    @Test
    void shouldCreateCompany() {
        CreateCompanyRequest request = new CreateCompanyRequest("Itaú Unibanco");
        
        ResponseEntity<CompanyResponse> response = restTemplate.postForEntity("/companies", request, CompanyResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Itaú Unibanco", response.getBody().name());
        assertNotNull(response.getBody().id());
    }

    @Test
    void shouldUpdateCompany(){
        CreateCompanyRequest request = new CreateCompanyRequest("Itaú Trocar");
        
        ResponseEntity<CompanyResponse> response = restTemplate.postForEntity("/companies", request, CompanyResponse.class);
        Long id = response.getBody().id();

        CreateCompanyRequest requestUpdate = CreateCompanyRequest.builder().name("Itaú Atualizado").build();
        restTemplate.put("/companies/" + id, requestUpdate);
        ResponseEntity<CompanyResponse> getResponse = restTemplate.getForEntity("/companies/" + id, CompanyResponse.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Itaú Atualizado", getResponse.getBody().name());        
    }

    @Test
    void shouldDeleteCompany(){
        CreateCompanyRequest request = new CreateCompanyRequest("Itaú Deletar");
        
        ResponseEntity<CompanyResponse> response = restTemplate.postForEntity("/companies", request, CompanyResponse.class);
        Long id = response.getBody().id();

        restTemplate.delete("/companies/" + id);

        ResponseEntity<ErrorResponse> getResponse = restTemplate.getForEntity("/companies/" + id, ErrorResponse.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
    
}
