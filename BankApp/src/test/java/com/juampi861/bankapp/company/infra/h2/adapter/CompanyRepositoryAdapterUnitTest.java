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

    @Mock
    private CompanyJpaRepository companyJpaRepository;

    @Mock
    private CompanyEntityMapper companyEntityMapper;

    @InjectMocks
    private CompanyRepositoryAdapter companyRepositoryAdapter;

    private final LocalDateTime testDate = LocalDateTime.now();
    private final String testCuit = "30-12345678-9";
    private Company testCompany;
    private CompanyEntity testEntity;

    @BeforeEach
    void setUp() {
        testCompany = new Company();
        testCompany.setCuit(testCuit);

        testEntity = new CompanyEntity();
        testEntity.setCuit(testCuit);
    }

    @Test
    void findCompaniesByCreatedTimeAfter_shouldReturnMappedCompanies() {
        when(companyJpaRepository.findByCreatedTimeAfter(testDate))
                .thenReturn(List.of(testEntity));
        when(companyEntityMapper.fromEntityToCompany(testEntity))
                .thenReturn(testCompany);

        final List<Company> result = companyRepositoryAdapter.findCompaniesByCreatedTimeAfter(testDate);

        assertEquals(1, result.size());
        assertEquals(testCompany, result.get(0));

        verify(companyJpaRepository).findByCreatedTimeAfter(testDate);
        verify(companyEntityMapper).fromEntityToCompany(testEntity);
    }

    @Test
    void findCompaniesByCreatedTimeAfter_shouldReturnEmptyListWhenNoCompaniesFound() {
        when(companyJpaRepository.findByCreatedTimeAfter(testDate))
                .thenReturn(Collections.emptyList());

        final List<Company> result = companyRepositoryAdapter.findCompaniesByCreatedTimeAfter(testDate);

        assertTrue(result.isEmpty());
    }

    @Test
    void findCompaniesWithTransactionsAfterDate_shouldReturnMappedCompanies() {
        // Arrange
        when(companyJpaRepository.findCompaniesWithTransactionsAfterDate(testDate))
                .thenReturn(List.of(testEntity));
        when(companyEntityMapper.fromEntityToCompany(testEntity))
                .thenReturn(testCompany);

        List<Company> result = companyRepositoryAdapter.findCompaniesWithTransactionsAfterDate(testDate);

        assertEquals(1, result.size());
        assertEquals(testCompany, result.get(0));

        verify(companyJpaRepository).findCompaniesWithTransactionsAfterDate(testDate);
    }

    @Test
    void saveCompany_shouldMapAndSaveEntity() {
        when(companyEntityMapper.fromCompanyToEntity(testCompany))
                .thenReturn(testEntity);
        companyRepositoryAdapter.saveCompany(testCompany);

        verify(companyEntityMapper).fromCompanyToEntity(testCompany);
        verify(companyJpaRepository).save(testEntity);
    }

    @Test
    void findCompanyByCuit_shouldReturnMappedCompanyWhenExists() {
        when(companyJpaRepository.findById(testCuit))
                .thenReturn(Optional.of(testEntity));
        when(companyEntityMapper.fromEntityToCompany(testEntity))
                .thenReturn(testCompany);
        final Optional<Company> result = companyRepositoryAdapter.findCompanyByCuit(testCuit);

        assertTrue(result.isPresent());
        assertEquals(testCompany, result.get());
    }

    @Test
    void findCompanyByCuit_shouldReturnEmptyWhenNotFound() {
        when(companyJpaRepository.findById(testCuit))
                .thenReturn(Optional.empty());

        final Optional<Company> result = companyRepositoryAdapter.findCompanyByCuit(testCuit);
        assertTrue(result.isEmpty());
    }
}