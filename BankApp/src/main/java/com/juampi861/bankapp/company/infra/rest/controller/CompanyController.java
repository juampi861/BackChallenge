package com.juampi861.bankapp.company.infra.rest.controller;

import com.juampi861.bankapp.company.application.*;
import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.infra.rest.dto.*;
import com.juampi861.bankapp.company.infra.rest.mapper.CompanyDTOMapper;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Tag(name = "Company Management", description = "Company Endpoints")
@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
@Validated
public class CompanyController {
    private static final String DATE_TIME_FORMAT = "dd-MM-yy:HH-mm";
    private final CompanyDTOMapper mapper;
    private final FindCompaniesInteractor findCompaniesInteractor;
    private final AddCompanyInteractor addCompanyInteractor;

    @PreAuthorize("hasRole('bankUser')")
    @GetMapping
    @Operation(summary = "Get Companies created after a given date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Companies Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CompanyDTO.class))),
            @ApiResponse(responseCode = "204", description = "No companies found")
    })
    public ResponseEntity<List<CompanyDTO>> getCompaniesAfterDate(@Parameter(description = "Date and Time with this format:dd-MM-yy:HH-mm", example = "01-01-23:14-30")
                                                                  @RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) @PastOrPresent(message = "Invalid Date") final LocalDateTime createdAfterDateTime
    ) {
        final List<Company> companies = findCompaniesInteractor.findCompaniesByCreatedTimeAfter(createdAfterDateTime);
        if (CollectionUtils.isEmpty(companies)) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        final List<CompanyDTO> response = companies.stream().map(mapper::fromCompanyToCompanyDTO).toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Companies with transactions done after a given date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Companies found"),
            @ApiResponse(responseCode = "204", description = "No Companies found")
    })
    @PreAuthorize("hasRole('bankUser')")
    @GetMapping("/withTransactions")
    public ResponseEntity<List<CompanyDTO>> getCompaniesWithTransactionsAfterDate(@Parameter(description = "Date with format  dd-MM-yy:HH-mm", example = "01-01-23:14-30") @RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) @PastOrPresent(message = "Invalid Date") final LocalDateTime transactionsAfterDateTime) {
        final List<Company> companies = findCompaniesInteractor.findCompaniesWithTransactionsAfterDate(transactionsAfterDateTime);
        if (CollectionUtils.isEmpty(companies)) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        final List<CompanyDTO> response = companies.stream().map(mapper::fromCompanyToCompanyDTO).toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Creates a new Company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters")
    })
    @PreAuthorize("hasRole('bankUser')")
    @PostMapping("/create")
    public ResponseEntity<List<String>> createCompany(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Data needed to create the Company",
            required = true,
            content = @Content(schema = @Schema(implementation = CreateCompanyRequestDTO.class)))
                                                      @Valid @RequestBody final CreateCompanyRequestDTO request) {
        try {
            addCompanyInteractor.addCompany(mapper.fromCreateCompanyRequestToCompany(request));
            return ResponseEntity.ok(Collections.singletonList(("Company successfully created")));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonList(e.getMessage()));
        }
    }
}