package com.juampi861.bankapp.company.infra.rest.mapper;

import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.infra.rest.dto.*;
import org.springframework.stereotype.Component;

@Component
public class CompanyDTOMapper {
    public CompanyDTO fromCompanyToCompanyDTO(final Company company) {
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(company.getName());
        companyDTO.setCuit(company.getCuit());
        companyDTO.setCreatedTime(company.getCreatedTime());
        return companyDTO;
    }

    public Company fromCreateCompanyRequestToCompany(final CreateCompanyRequestDTO createCompanyRequestDTO) {
        final Company company = new Company();
        company.setCuit(createCompanyRequestDTO.getCuit());
        company.setName(createCompanyRequestDTO.getName());
        return company;
    }
}
