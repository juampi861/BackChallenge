package com.juampi861.bankapp.transactions.infra.database.h2.mapper;

import com.juampi861.bankapp.company.infra.database.h2.mapper.CompanyEntityMapper;
import com.juampi861.bankapp.transactions.domain.model.Transaction;
import com.juampi861.bankapp.transactions.infra.database.h2.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransactionEntityMapper {

    private final CompanyEntityMapper companyEntityMapper;

    public Transaction fromEntityToTransaction(final TransactionEntity entity) {
        final Transaction transaction = new Transaction();
        transaction.setTransactionDate(entity.getTransactionDate());
        transaction.setUid(entity.getUid());
        transaction.setAmount(entity.getAmount());
        transaction.setCompany(companyEntityMapper.fromEntityToCompany(entity.getCompany()));
        transaction.setDebitAccount(entity.getDebitAccount());
        transaction.setCreditAccount(entity.getCreditAccount());
        return transaction;
    }
}
