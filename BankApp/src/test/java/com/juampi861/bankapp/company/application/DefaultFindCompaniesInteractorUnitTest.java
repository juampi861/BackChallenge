package com.juampi861.bankapp.company.application;


import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.domain.repository.CompanyRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultFindCompaniesInteractorUnitTest {
    private static final String CUIT_1 = "11111111111";
    private static final String COMPANY_NAME_1 = "company1";
    private static final String CUIT_2 = "22222222222";
    private static final String COMPANY_NAME_2 = "company2";
    @Mock
    private CompanyRepositoryPort companyRepositoryPort;

    @InjectMocks
    private DefaultFindCompaniesInteractor findCompanyInteractor;

    @Test
    void findCompaniesByCreatedTimeAfter_shouldReturnCompanies_whenRepositoryReturnsData() {
        final LocalDateTime testDate = LocalDateTime.now();
        final Company company1 = new Company(CUIT_1, COMPANY_NAME_1);
        final Company company2 = new Company(CUIT_2, COMPANY_NAME_2);
        final List<Company> expectedCompanies = Arrays.asList(company1, company2);

        when(companyRepositoryPort.findCompaniesByCreatedTimeAfter(testDate))
                .thenReturn(expectedCompanies);


        final List<Company> result = findCompanyInteractor.findCompaniesByCreatedTimeAfter(testDate);

        assertEquals(expectedCompanies, result);
        verify(companyRepositoryPort, times(1))
                .findCompaniesByCreatedTimeAfter(testDate);
        verifyNoMoreInteractions(companyRepositoryPort);
    }

    @Test
    void findCompaniesByCreatedTimeAfter_shouldReturnEmptyList_whenRepositoryReturnsEmpty() {
        final LocalDateTime testDate = LocalDateTime.now();

        when(companyRepositoryPort.findCompaniesByCreatedTimeAfter(testDate))
                .thenReturn(Collections.emptyList());

        final List<Company> result = findCompanyInteractor.findCompaniesByCreatedTimeAfter(testDate);

        assertTrue(result.isEmpty());
        verify(companyRepositoryPort, times(1))
                .findCompaniesByCreatedTimeAfter(testDate);
    }

    @Test
    void findCompaniesWithTransactionsAfterDate_shouldReturnCompanies_whenRepositoryReturnsData() {
        final LocalDateTime testDate = LocalDateTime.now();
        final Company company1 = new Company(CUIT_1, COMPANY_NAME_1);
        final Company company2 = new Company(CUIT_2, COMPANY_NAME_2);
        final List<Company> expectedCompanies = Arrays.asList(company1, company2);

        when(companyRepositoryPort.findCompaniesWithTransactionsAfterDate(testDate))
                .thenReturn(expectedCompanies);

        final List<Company> result = findCompanyInteractor.findCompaniesWithTransactionsAfterDate(testDate);

        assertEquals(expectedCompanies, result);
        verify(companyRepositoryPort, times(1))
                .findCompaniesWithTransactionsAfterDate(testDate);
        verifyNoMoreInteractions(companyRepositoryPort);
    }

    @Test
    void findCompaniesWithTransactionsAfterDate_shouldReturnEmptyList_whenRepositoryReturnsEmpty() {
        final LocalDateTime testDate = LocalDateTime.now();

        when(companyRepositoryPort.findCompaniesWithTransactionsAfterDate(testDate))
                .thenReturn(Collections.emptyList());

        final List<Company> result = findCompanyInteractor.findCompaniesWithTransactionsAfterDate(testDate);

        assertTrue(result.isEmpty());
        verify(companyRepositoryPort, times(1))
                .findCompaniesWithTransactionsAfterDate(testDate);
    }

    @Test
    void findCompaniesWithTransactionsAfterDate_shouldCallRepositoryWithCorrectParameter() {
        final LocalDateTime testDate = LocalDateTime.now();

        when(companyRepositoryPort.findCompaniesWithTransactionsAfterDate(testDate))
                .thenReturn(Collections.emptyList());

        findCompanyInteractor.findCompaniesWithTransactionsAfterDate(testDate);

        verify(companyRepositoryPort, times(1))
                .findCompaniesWithTransactionsAfterDate(testDate);
    }
}