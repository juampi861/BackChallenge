package com.juampi861.bankapp.transactions.application;

import com.juampi861.bankapp.transactions.domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Find Transactions Interactor
 */
public interface FindTransactionsInteractor {
    /**
     * Finds Transactions done after a given date and time
     *
     * @param localDateTime The given date and time
     * @return a List of the transactions found
     */
    List<Transaction> findTransactionsAfterDate(LocalDateTime localDateTime);
}
