package com.juampi861.bankapp.company.infra.database.h2.mapper;

import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.company.infra.database.h2.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyEntityMapper {
    /**
     * Converts a Company Entity into a Company
     *
     * @param entity The given Entity
     * @return The Company
     */
    public Company fromEntityToCompany(final CompanyEntity entity) {
        return new Company(entity.getCuit(), entity.getName(), entity.getCreatedTime());
    }

    /**
     * Converts a Company into a CompanyEntity
     *
     * @param company The given Company
     * @return The Company Entity
     */
    public CompanyEntity fromCompanyToEntity(final Company company) {
        final CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCreatedTime(company.getCreatedTime());
        companyEntity.setCuit(company.getCuit());
        companyEntity.setName(company.getName());
        return companyEntity;
    }
}