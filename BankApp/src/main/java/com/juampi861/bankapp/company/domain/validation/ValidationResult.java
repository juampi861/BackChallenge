package com.juampi861.bankapp.company.domain.validation;

import com.juampi861.bankapp.company.domain.exceptions.ValidationError;

import java.util.*;

public class ValidationResult {
    private final List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void throwIfInvalid() throws ValidationError {
        if (!isValid()) {
            throw new ValidationError(errors);
        }
    }
}
