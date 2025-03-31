package com.juampi861.bankapp.company.application;

import com.juampi861.bankapp.company.domain.exceptions.*;
import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.domain.repository.CompanyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultAddCompanyInteractor implements AddCompanyInteractor {
    private static final String COMPANY_ALREADY_EXISTS_MSG = "The company already exists";

    private final CompanyRepositoryPort companyRepositoryPort;

    @Override
    public void addCompany(final Company company) {
        if (companyRepositoryPort.findCompanyByCuit(company.getCuit()).isPresent()) {
            log.warn("The company {} already exists", company.getCuit());
            throw new CompanyAlreadyExistsException(COMPANY_ALREADY_EXISTS_MSG);
        }
        log.debug("Saving the new Company with cuit {}", company.getCuit());
        company.setCreatedTime(LocalDateTime.now());
        companyRepositoryPort.saveCompany(company);
    }
}