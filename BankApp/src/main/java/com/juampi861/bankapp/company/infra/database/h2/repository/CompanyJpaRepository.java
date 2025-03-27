package com.juampi861.bankapp.company.infra.database.h2.repository;

import com.juampi861.bankapp.company.infra.database.h2.entity.CompanyEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, String> {
    List<CompanyEntity> findByCreatedTimeAfter(LocalDateTime maxDate);
    @Query("SELECT DISTINCT t.company FROM TransactionEntity t WHERE t.transactionDate > :maxDate")
    List<CompanyEntity> findCompaniesWithTransactionsAfterDate(@Param("maxDate") LocalDateTime minDate);
}
