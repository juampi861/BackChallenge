package com.juampi861.bankapp.transactions.domain.repository;

import com.juampi861.bankapp.transactions.domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Transaction Repository Port
 */
public interface TransactionRepositoryPort {
    /**
     * Finds Transactions created after a given Date and Time
     *
     * @param maxDate The given Date and Time
     * @return The transactions found
     */
    List<Transaction> findTransactionsCreatedTimeAfter(LocalDateTime maxDate);
}
