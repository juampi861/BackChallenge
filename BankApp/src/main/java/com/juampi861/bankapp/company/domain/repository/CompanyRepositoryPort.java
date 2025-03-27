package com.juampi861.bankapp.company.domain.repository;

import com.juampi861.bankapp.company.domain.model.Company;

import java.time.LocalDateTime;
import java.util.*;

/**
 * The Company Repository Port
 */
public interface CompanyRepositoryPort {
    /**
     * Finds Companies created after a given date and time
     *
     * @param maxDate the given date
     * @return The Companies found
     */
    List<Company> findCompaniesByCreatedTimeAfter(LocalDateTime maxDate);

    /**
     * Finds Companies with transactions done after a given date and time
     *
     * @param maxDate the given date
     * @return the transactions found
     */
    List<Company> findCompaniesWithTransactionsAfterDate(LocalDateTime maxDate);

    /**
     * Saves a given Company
     *
     * @param company the given Company
     */
    void saveCompany(Company company);

    /**
     * Finds a Company by the given CUIT
     *
     * @param cuit the given CUIT
     * @return Optional of Company
     */
    Optional<Company> findCompanyByCuit(String cuit);
}
