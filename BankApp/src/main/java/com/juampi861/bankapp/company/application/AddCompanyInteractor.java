package com.juampi861.bankapp.company.application;

import com.juampi861.bankapp.company.domain.model.Company;

/**
 * The Add Company Interactor
 */
public interface AddCompanyInteractor {

    /**
     * Adds a new Company
     *
     * @param company The given Company
     */
    void addCompany(Company company);
}
