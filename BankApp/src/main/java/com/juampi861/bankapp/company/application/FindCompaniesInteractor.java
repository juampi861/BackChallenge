package com.juampi861.bankapp.company.application;

import com.juampi861.bankapp.company.domain.model.Company;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Find Company Interactor
 */
public interface FindCompaniesInteractor {
    /**
     * Find compamnies created after a given date and time
     *
     * @param maxDate the given date and time
     * @return the companies found created after the given time
     */
    List<Company> findCompaniesByCreatedTimeAfter(LocalDateTime maxDate);

    /**
     * Find Companies with transactions after a given date and time
     *
     * @param maxDate the given date and time
     * @return the companies found
     */
    List<Company> findCompaniesWithTransactionsAfterDate(LocalDateTime maxDate);
}
