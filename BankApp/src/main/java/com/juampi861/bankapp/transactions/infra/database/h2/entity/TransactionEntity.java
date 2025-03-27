package com.juampi861.bankapp.transactions.infra.database.h2.entity;

import com.juampi861.bankapp.company.infra.database.h2.entity.CompanyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uid;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_cuit")
    private CompanyEntity company;
    @Column(name = "debit_account")
    private String debitAccount;
    @Column(name = "credit_account")
    private String creditAccount;
}
