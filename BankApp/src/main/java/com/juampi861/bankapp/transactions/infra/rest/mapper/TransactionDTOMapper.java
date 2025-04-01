package com.juampi861.bankapp.transactions.infra.rest.mapper;

import com.juampi861.bankapp.company.infra.rest.mapper.CompanyDTOMapper;
import com.juampi861.bankapp.transactions.domain.model.Transaction;
import com.juampi861.bankapp.transactions.infra.rest.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class TransactionDTOMapper {

    private static final String DATE_FORMAT = "yyyy-MM-dd:HH-ss";
    private final CompanyDTOMapper companyDTOMapper;

    public TransactionDTO transactionToTransactionDTO(final Transaction source) {
        TransactionDTO target = new TransactionDTO();
        target.setUid(source.getUid());
        target.setCompany(companyDTOMapper.fromCompanyToCompanyDTO(source.getCompany()));
        target.setAmount(source.getAmount());
        target.setDebitAccount(source.getDebitAccount());
        target.setCreditAccount(source.getCreditAccount());
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        target.setTransactionDate(source.getTransactionDate().format(formatter));
        return target;
    }
}