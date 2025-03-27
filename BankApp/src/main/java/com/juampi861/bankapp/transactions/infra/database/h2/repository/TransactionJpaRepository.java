package com.juampi861.bankapp.transactions.infra.database.h2.repository;

import com.juampi861.bankapp.transactions.infra.database.h2.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findByTransactionDateAfter(LocalDateTime maxDate);
}
