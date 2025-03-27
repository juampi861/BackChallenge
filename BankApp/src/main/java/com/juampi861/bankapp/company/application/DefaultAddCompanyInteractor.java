package com.juampi861.bankapp.company.application;

import com.juampi861.bankapp.company.domain.exceptions.*;
import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.domain.repository.CompanyRepositoryPort;
import com.juampi861.bankapp.company.domain.validation.ValidationResult;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultAddCompanyInteractor implements AddCompanyInteractor {
    private static final String CUIT_INVALID_ERROR = "A valid CUIT is required";
    private static final String NAME_INVALID_ERROR = "A Valid Name is required";
    private static final String COMPANY_ALREADY_EXISTS_MSG = "The company already exists";

    private final CompanyRepositoryPort companyRepositoryPort;

    @Override
    public void addCompany(final Company company) throws ValidationError {
        validateCompany(company).throwIfInvalid();
        if (companyRepositoryPort.findCompanyByCuit(company.getCuit()).isPresent()) {
            log.warn("The company {} already exists", company.getCuit());
            throw new CompanyAlreadyExistsException(COMPANY_ALREADY_EXISTS_MSG);
        }
        company.setCreatedTime(LocalDateTime.now());
        log.debug("Saving the new Company with cuit {}", company.getCuit());
        companyRepositoryPort.saveCompany(company);
    }

    private ValidationResult validateCompany(final Company company) {
        final ValidationResult validationResult = new ValidationResult();
        if (StringUtils.isEmpty(company.getCuit())) {
            validationResult.addError(CUIT_INVALID_ERROR);
        }
        if (StringUtils.isEmpty(company.getName())) {
            validationResult.addError(NAME_INVALID_ERROR);
        }
        return validationResult;
    }
}