package com.juampi861.bankapp.company.domain.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationError extends Throwable {
    private final List<String> errors;

    public ValidationError(List<String> errors) {
        super("Validation failed: " + String.join(", ", errors));
        this.errors = errors;
    }

}
