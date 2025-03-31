package com.juampi861.bankapp.transactions.infra.rest.dto;

import com.juampi861.bankapp.company.infra.rest.dto.CompanyDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID uid;
    private String transactionDate;
    private BigDecimal amount;
    private CompanyDTO company;
    private String debitAccount;
    private String creditAccount;
}
