package com.juampi861.bankapp.company.infra.database.h2.adapter;

import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.domain.repository.CompanyRepositoryPort;
import com.juampi861.bankapp.company.infra.database.h2.mapper.CompanyEntityMapper;
import com.juampi861.bankapp.company.infra.database.h2.repository.CompanyJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class CompanyRepositoryAdapter implements CompanyRepositoryPort {
    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyEntityMapper companyEntityMapper;

    @Override
    public List<Company> findCompaniesByCreatedTimeAfter(LocalDateTime maxDate) {
        log.debug("Looking for companies created after: {}", maxDate);
        List<Company> companies = companyJpaRepository.findByCreatedTimeAfter(maxDate).stream()
                .map(companyEntityMapper::fromEntityToCompany)
                .toList();
        log.debug("Companies found: {}", companies.size());
        return companies;
    }

    @Override
    public List<Company> findCompaniesWithTransactionsAfterDate(LocalDateTime maxDate) {
        log.debug("Looking for Companies with transactions after: {}", maxDate);
        List<Company> companies = companyJpaRepository.findCompaniesWithTransactionsAfterDate(maxDate).stream()
                .map(companyEntityMapper::fromEntityToCompany)
                .toList();
        log.debug("Companies found: {}", companies.size());
        return companies;
    }

    @Override
    public void saveCompany(Company company) {
        log.info("Saving Company with CUIT: {}", company.getCuit());

        companyJpaRepository.save(companyEntityMapper.fromCompanyToEntity(company));

        log.debug("Company successfully saved");
    }

    @Override
    public Optional<Company> findCompanyByCuit(String cuit) {
        return companyJpaRepository.findById(cuit).map(companyEntityMapper::fromEntityToCompany);
    }
}