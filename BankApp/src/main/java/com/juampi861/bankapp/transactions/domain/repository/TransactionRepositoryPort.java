package com.juampi861.bankapp.transactions.domain.repository;

import com.juampi861.bankapp.transactions.domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepositoryPort {
    List<Transaction> findTransactionsCreatedTimeAfter(LocalDateTime maxDate);
}
