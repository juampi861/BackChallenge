package com.juampi861.bankapp.transactions.domain.model;

import com.juampi861.bankapp.company.domain.model.Company;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    private UUID uid;
    private LocalDateTime transactionDate;
    private BigDecimal amount;
    private Company company;
    private String debitAccount;
    private String creditAccount;
}
