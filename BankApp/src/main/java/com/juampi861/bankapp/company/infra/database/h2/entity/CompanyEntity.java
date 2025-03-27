package com.juampi861.bankapp.company.infra.database.h2.entity;

import com.juampi861.bankapp.transactions.infra.database.h2.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "companies")
public class CompanyEntity {
    @Id
    private String cuit;
    private String name;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
}
