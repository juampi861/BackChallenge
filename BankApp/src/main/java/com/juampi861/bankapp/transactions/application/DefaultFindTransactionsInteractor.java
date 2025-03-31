package com.juampi861.bankapp.transactions.application;

import com.juampi861.bankapp.transactions.domain.model.Transaction;
import com.juampi861.bankapp.transactions.domain.repository.TransactionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DefaultFindTransactionsInteractor implements FindTransactionsInteractor {

    private final TransactionRepositoryPort transactionRepositoryPort;

    @Override
    public List<Transaction> findTransactionsAfterDate(final LocalDateTime afterDate) {
        return transactionRepositoryPort.findTransactionsCreatedTimeAfter(afterDate);
    }
}