package com.juampi861.bankapp.company.infra.rest;

import com.juampi861.bankapp.company.application.*;
import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.infra.rest.controller.CompanyController;
import com.juampi861.bankapp.company.infra.rest.dto.*;
import com.juampi861.bankapp.company.infra.rest.mapper.CompanyDTOMapper;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CompanyControllerTest {
    @Mock
    private FindCompaniesInteractor findCompaniesInteractor;

    @Mock
    private AddCompanyInteractor addCompanyInteractor;

    @Mock
    private CompanyDTOMapper mapper;

    @InjectMocks
    private CompanyController companyController;

    @Test
    void getCompaniesAfterDate_ReturnsEmptyList_WhenNoCompaniesFound() {
        final LocalDateTime date = LocalDateTime.now();
        when(findCompaniesInteractor.findCompaniesByCreatedTimeAfter(date))
                .thenReturn(Collections.emptyList());

        final ResponseEntity<List<CompanyDTO>> response =
                companyController.getCompaniesAfterDate(date);

        assertTrue(response.getBody().isEmpty());
        verify(findCompaniesInteractor).findCompaniesByCreatedTimeAfter(date);
    }

    @Test
    void getCompaniesAfterDate_ReturnsCompanies_WhenFound() {
        final LocalDateTime date = LocalDateTime.now();
        final Company company = new Company("12345678901", "Test Company");
        final CompanyDTO dto = new CompanyDTO();

        when(findCompaniesInteractor.findCompaniesByCreatedTimeAfter(date))
                .thenReturn(List.of(company));
        when(mapper.fromCompanyToCompanyDTO(company))
                .thenReturn(dto);

        final ResponseEntity<List<CompanyDTO>> response =
                companyController.getCompaniesAfterDate(date);

        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals(dto, response.getBody().get(0));
    }

    @Test
    void getCompaniesWithTransactionsAfterDate_ReturnsEmptyList_WhenNoCompaniesFound() {
        final LocalDateTime date = LocalDateTime.now();
        when(findCompaniesInteractor.findCompaniesWithTransactionsAfterDate(date))
                .thenReturn(Collections.emptyList());

        final ResponseEntity<List<CompanyDTO>> response =
                companyController.getCompaniesWithTransactionsAfterDate(date);

        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void createCompany_ReturnsSuccessMessage_WhenValidRequest() {
        final CreateCompanyRequestDTO request = new CreateCompanyRequestDTO();
        request.setCuit("12345678901");
        request.setName("Test Company");

        final Company company = new Company("12345678901", "Test Company");

        when(mapper.fromCreateCompanyRequestToCompany(request))
                .thenReturn(company);

        final ResponseEntity<List<String>> response =
                companyController.createCompany(request);

        assertEquals("Company successfully created", response.getBody().get(0));
        verify(addCompanyInteractor).addCompany(company);
    }

    @Test
    void createCompany_ReturnsBadRequest_WhenInvalidRequest() {
        final CreateCompanyRequestDTO request = new CreateCompanyRequestDTO();
        request.setCuit("invalid");
        request.setName("Test Company");
        final String errorMessage = "A valid CUIT is required";

        when(mapper.fromCreateCompanyRequestToCompany(request))
                .thenThrow(new IllegalArgumentException(errorMessage));

        final ResponseEntity<List<String>> response =
                companyController.createCompany(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().get(0));
    }
}