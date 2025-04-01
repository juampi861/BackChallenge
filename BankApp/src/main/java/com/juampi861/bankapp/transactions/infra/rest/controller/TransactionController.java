package com.juampi861.bankapp.transactions.infra.rest.controller;

import com.juampi861.bankapp.transactions.application.FindTransactionsInteractor;
import com.juampi861.bankapp.transactions.domain.model.Transaction;
import com.juampi861.bankapp.transactions.infra.rest.dto.TransactionDTO;
import com.juampi861.bankapp.transactions.infra.rest.mapper.TransactionDTOMapper;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Tag(name = "Transaction Management", description = "Transaction Endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private static final String DATE_TIME_FORMAT = "dd-MM-yy:HH-mm";
    private final TransactionDTOMapper mapper;
    private final FindTransactionsInteractor transactionsInteractor;

    @Operation(summary = "Get Transactions created after a given date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionDTO.class))),
            @ApiResponse(responseCode = "204", description = "No transactions found")
    })
    @PreAuthorize("hasRole('bankUser')")
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getTransactionsAfterDate(@Parameter(description = "Date and Time with this format:dd-MM-yy:HH-mm", example = "01-01-23:14-30")
                                                                         @RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) @PastOrPresent(message = "Invalid Date") final LocalDateTime afterDate) {
        final List<Transaction> transactions = transactionsInteractor.findTransactionsAfterDate(afterDate);
        if (CollectionUtils.isEmpty(transactions)) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        final List<TransactionDTO> response = transactions.stream().map(mapper::transactionToTransactionDTO).toList();
        return ResponseEntity.ok(response);
    }
}