package com.juampi861.bankapp.company.domain.model;

import com.juampi861.bankapp.transactions.domain.model.Transaction;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Company {
    private String cuit;
    private String name;
    private LocalDateTime createdTime;
}
