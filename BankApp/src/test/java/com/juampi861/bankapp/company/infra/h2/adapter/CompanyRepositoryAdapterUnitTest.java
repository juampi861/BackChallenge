package com.juampi861.bankapp.company.infra.h2.adapter;

import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.infra.database.h2.adapter.CompanyRepositoryAdapter;
import com.juampi861.bankapp.company.infra.database.h2.entity.CompanyEntity;
import com.juampi861.bankapp.company.infra.database.h2.mapper.CompanyEntityMapper;
import com.juampi861.bankapp.company.infra.database.h2.repository.CompanyJpaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyRepositoryAdapterTest {

    private static final String COMPANY_NAME = "companyName";
    private static final LocalDateTime TEST_DATE = LocalDateTime.now();
    private static final String TEST_CUIT = "30123456789";
    @Mock
    private CompanyJpaRepository companyJpaRepository;

    @Mock
    private CompanyEntityMapper companyEntityMapper;

    @InjectMocks
    private CompanyRepositoryAdapter companyRepositoryAdapter;

    private Company company;
    private CompanyEntity testEntity;

    @BeforeEach
    void setUp() {
        company = new Company(TEST_CUIT,COMPANY_NAME);
        company.setCuit(TEST_CUIT);

        testEntity = new CompanyEntity();
        testEntity.setCuit(TEST_CUIT);
    }

    @Test
    void findCompaniesByCreatedTimeAfter_shouldReturnMappedCompanies() {
        when(companyJpaRepository.findByCreatedTimeAfter(TEST_DATE))
                .thenReturn(List.of(testEntity));
        when(companyEntityMapper.fromEntityToCompany(testEntity))
                .thenReturn(company);

        final List<Company> result = companyRepositoryAdapter.findCompaniesByCreatedTimeAfter(TEST_DATE);

        assertEquals(1, result.size());
        assertEquals(company, result.get(0));

        verify(companyJpaRepository).findByCreatedTimeAfter(TEST_DATE);
        verify(companyEntityMapper).fromEntityToCompany(testEntity);
    }

    @Test
    void findCompaniesByCreatedTimeAfter_shouldReturnEmptyListWhenNoCompaniesFound() {
        when(companyJpaRepository.findByCreatedTimeAfter(TEST_DATE))
                .thenReturn(Collections.emptyList());

        final List<Company> result = companyRepositoryAdapter.findCompaniesByCreatedTimeAfter(TEST_DATE);

        assertTrue(result.isEmpty());
    }

    @Test
    void findCompaniesWithTransactionsAfterDate_shouldReturnMappedCompanies() {
        // Arrange
        when(companyJpaRepository.findCompaniesWithTransactionsAfterDate(TEST_DATE))
                .thenReturn(List.of(testEntity));
        when(companyEntityMapper.fromEntityToCompany(testEntity))
                .thenReturn(company);

        List<Company> result = companyRepositoryAdapter.findCompaniesWithTransactionsAfterDate(TEST_DATE);

        assertEquals(1, result.size());
        assertEquals(company, result.get(0));

        verify(companyJpaRepository).findCompaniesWithTransactionsAfterDate(TEST_DATE);
    }

    @Test
    void saveCompany_shouldMapAndSaveEntity() {
        when(companyEntityMapper.fromCompanyToEntity(company))
                .thenReturn(testEntity);
        companyRepositoryAdapter.saveCompany(company);

        verify(companyEntityMapper).fromCompanyToEntity(company);
        verify(companyJpaRepository).save(testEntity);
    }

    @Test
    void findCompanyByCuit_shouldReturnMappedCompanyWhenExists() {
        when(companyJpaRepository.findById(TEST_CUIT))
                .thenReturn(Optional.of(testEntity));
        when(companyEntityMapper.fromEntityToCompany(testEntity))
                .thenReturn(company);
        final Optional<Company> result = companyRepositoryAdapter.findCompanyByCuit(TEST_CUIT);

        assertTrue(result.isPresent());
        assertEquals(company, result.get());
    }

    @Test
    void findCompanyByCuit_shouldReturnEmptyWhenNotFound() {
        when(companyJpaRepository.findById(TEST_CUIT))
                .thenReturn(Optional.empty());

        final Optional<Company> result = companyRepositoryAdapter.findCompanyByCuit(TEST_CUIT);
        assertTrue(result.isEmpty());
    }
}