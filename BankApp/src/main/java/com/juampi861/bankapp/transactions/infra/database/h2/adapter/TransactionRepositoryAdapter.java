package com.juampi861.bankapp.transactions.infra.database.h2.adapter;

import com.juampi861.bankapp.transactions.domain.model.Transaction;
import com.juampi861.bankapp.transactions.domain.repository.TransactionRepositoryPort;
import com.juampi861.bankapp.transactions.infra.database.h2.mapper.TransactionEntityMapper;
import com.juampi861.bankapp.transactions.infra.database.h2.repository.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {
    private final TransactionJpaRepository transactionJpaRepository;
    private final TransactionEntityMapper transactionEntityMapper;

    @Override
    public List<Transaction> findTransactionsCreatedTimeAfter(final LocalDateTime maxDate) {
        return transactionJpaRepository.findByTransactionDateAfter(maxDate).stream()
                .map(transactionEntityMapper::fromEntityToTransaction).toList();
    }
}