package com.juampi861.bankapp.company.application;

import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.domain.repository.CompanyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultFindCompanyInteractor implements FindCompanyInteractor {
    private final CompanyRepositoryPort companyRepositoryPort;

    @Override
    public List<Company> findCompaniesByCreatedTimeAfter(final LocalDateTime maxDate) {
        return companyRepositoryPort.findCompaniesByCreatedTimeAfter(maxDate);
    }

    @Override
    public List<Company> findCompaniesWithTransactionsAfterDate(LocalDateTime maxDate) {
        return companyRepositoryPort.findCompaniesWithTransactionsAfterDate(maxDate);
    }
}