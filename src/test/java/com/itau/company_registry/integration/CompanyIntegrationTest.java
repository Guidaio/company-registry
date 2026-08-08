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
    
}
