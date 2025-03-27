package com.juampi861.bankapp.company.domain.exceptions;

public class CompanyAlreadyExistsException extends RuntimeException{
    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
}
