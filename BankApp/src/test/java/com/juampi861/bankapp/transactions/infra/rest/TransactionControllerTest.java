package com.juampi861.bankapp.transactions.infra.rest;

import com.juampi861.bankapp.company.domain.model.Company;
import com.juampi861.bankapp.transactions.application.FindTransactionsInteractor;
import com.juampi861.bankapp.transactions.domain.model.Transaction;
import com.juampi861.bankapp.transactions.infra.rest.controller.TransactionController;
import com.juampi861.bankapp.transactions.infra.rest.dto.TransactionDTO;
import com.juampi861.bankapp.transactions.infra.rest.mapper.TransactionDTOMapper;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TransactionControllerTest {
    private static final String DATE_FORMAT = "yyyy-MM-dd:HH-ss";
    private static final double VALUE = 100.50;
    private static final String CREDIT_ACCOUNT = "CREDIT_ACC";
    private static final String DEBIT_ACCOUNT = "DEBIT_ACC";

    @Mock
    private FindTransactionsInteractor transactionsInteractor;

    @Mock
    private TransactionDTOMapper mapper;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void getTransactionsAfterDate_ReturnsEmptyList_WhenNoTransactionsFound() {
        final LocalDateTime afterDate = LocalDateTime.now();
        when(transactionsInteractor.findTransactionsAfterDate(afterDate))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<TransactionDTO>> response =
                transactionController.getTransactionsAfterDate(afterDate);

        assertTrue(response.getBody().isEmpty());
        verify(transactionsInteractor).findTransactionsAfterDate(afterDate);
    }

    @Test
    void getTransactionsAfterDate_ReturnsTransactions_WhenFound() {
        final LocalDateTime afterDate = LocalDateTime.now();
        final UUID transactionId = UUID.randomUUID();
        final BigDecimal amount = BigDecimal.valueOf(VALUE);

        final Transaction transaction = new Transaction(
                transactionId,
                afterDate,
                amount,
                mock(Company.class),
                CREDIT_ACCOUNT,
                DEBIT_ACCOUNT
        );

        final TransactionDTO dto = new TransactionDTO();
        dto.setUid(transactionId);
        dto.setAmount(amount);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        dto.setTransactionDate(formatter.format(afterDate));

        when(transactionsInteractor.findTransactionsAfterDate(afterDate))
                .thenReturn(List.of(transaction));
        when(mapper.transactionToTransactionDTO(transaction))
                .thenReturn(dto);

        final ResponseEntity<List<TransactionDTO>> response =
                transactionController.getTransactionsAfterDate(afterDate);

        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals(dto, response.getBody().get(0));
        assertEquals(transactionId, response.getBody().get(0).getUid());
        assertEquals(amount, response.getBody().get(0).getAmount());
    }
}