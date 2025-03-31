package com.juampi861.bankapp.transactions.domain.model;

import com.juampi861.bankapp.company.domain.model.Company;
import io.micrometer.common.util.StringUtils;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
public class Transaction {
    private static final String INVALID_UUID = "uid cannot be null";
    private static final String INVALID_AMOUNT = "Transaction amount cannot be negative";
    private static final String COMPANY_CANNOT_BE_NULL = "Company cannot be null";
    private static final String CREDIT_ACCOUNT_EMPTY = "Credit Account cannot be empty";
    private static final String DEBIT_ACCOUNT_EMPTY = "Debit Account cannot be empty";

    private static final String ERROR_SEPARATOR = ". ";

    private UUID uid;
    private LocalDateTime transactionDate;
    private BigDecimal amount;
    private Company company;
    private String debitAccount;
    private String creditAccount;

    public Transaction(final UUID uid, final LocalDateTime transactionDate, final BigDecimal amount, final Company company, final String creditAccount, final String debitAccount) {

        validateParams(uid, amount, company, creditAccount, debitAccount);

        this.uid = uid;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.company = company;
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
    }

    private void validateParams(final UUID uid, final BigDecimal amount, final Company company, final String creditAccount, final String debitAccount) {
        String stringError = Strings.EMPTY;
        if (Objects.isNull(uid)) {
            stringError = stringError.concat(INVALID_AMOUNT).concat(ERROR_SEPARATOR);
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            stringError = stringError.concat(INVALID_AMOUNT).concat(ERROR_SEPARATOR);
        }
        if (StringUtils.isEmpty(creditAccount)) {
            stringError = stringError.concat(CREDIT_ACCOUNT_EMPTY).concat(ERROR_SEPARATOR);
        }
        if (StringUtils.isEmpty(debitAccount)) {
            stringError = stringError.concat(DEBIT_ACCOUNT_EMPTY).concat(ERROR_SEPARATOR);
        }
        if (StringUtils.isNotEmpty(stringError)) {
            throw new IllegalArgumentException(stringError);
        }
    }
}