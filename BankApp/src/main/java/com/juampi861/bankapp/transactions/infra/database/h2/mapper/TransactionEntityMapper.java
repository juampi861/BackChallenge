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
        return new Transaction(entity.getUid(),
                entity.getTransactionDate(),
                entity.getAmount(),
                companyEntityMapper.fromEntityToCompany(entity.getCompany()),
                entity.getCreditAccount(),
                entity.getDebitAccount());
    }
}
