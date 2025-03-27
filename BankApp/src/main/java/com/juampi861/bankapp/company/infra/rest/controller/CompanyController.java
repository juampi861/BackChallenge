package com.juampi861.bankapp.company.infra.rest.controller;

import com.juampi861.bankapp.company.application.*;
import com.juampi861.bankapp.company.domain.exceptions.ValidationError;
import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.infra.rest.dto.*;
import com.juampi861.bankapp.company.infra.rest.mapper.CompanyDTOMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyController {
    private static final String DATE_TIME_FORMAT = "dd-MM-yy:HH-mm";
    private final CompanyDTOMapper mapper;
    private final FindCompanyInteractor findCompanyInteractor;
    private final AddCompanyInteractor addCompanyInteractor;

    @PreAuthorize("hasRole('bankUser')")
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompaniesAfterDate(
            @RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) final LocalDateTime createdAfterDateTime
    ) {
        final List<Company> companies = findCompanyInteractor.findCompaniesByCreatedTimeAfter(createdAfterDateTime);
        if (CollectionUtils.isEmpty(companies)) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        final List<CompanyDTO> response = companies.stream().map(mapper::fromCompanyToCompanyDTO).toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('bankUser')")
    @GetMapping("/withTransactions")
    public ResponseEntity<List<CompanyDTO>> getCompaniesWithTransactionsAfterDate(@RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) final LocalDateTime transactionsAfterDateTime) {
        final List<Company> companies = findCompanyInteractor.findCompaniesWithTransactionsAfterDate(transactionsAfterDateTime);
        if (CollectionUtils.isEmpty(companies)) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        final List<CompanyDTO> response = companies.stream().map(mapper::fromCompanyToCompanyDTO).toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('bankUser')")
    @PostMapping("/create")
    public ResponseEntity<List<String>> createCompany(@RequestBody final CreateCompanyRequestDTO request) {
        try {
            addCompanyInteractor.addCompany(mapper.fromCreateCompanyRequestToCompany(request));
            return ResponseEntity.ok(Collections.singletonList(("Company successfully created")));
        } catch (ValidationError e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getErrors());
        }
    }
}