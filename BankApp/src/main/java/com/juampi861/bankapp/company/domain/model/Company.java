package com.juampi861.bankapp.company.domain.model;

import io.micrometer.common.util.StringUtils;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class Company {

    private static final String CUIT_INVALID_ERROR = "A valid CUIT is required";
    private static final String NAME_INVALID_ERROR = "A Valid Name is required";

    private String cuit;
    private String name;
    private LocalDateTime createdTime;

    public Company(final String cuit, final String name) {
        validateCuitAndName(cuit, name);
        this.setCuit(cuit);
        this.setName(name);
    }

    public Company(final String cuit, final String name, final LocalDateTime createdTime) {
        validateCuitAndName(cuit, name);
        this.setCuit(cuit);
        this.setName(name);
        this.setCreatedTime(createdTime);
    }

    private void validateCuitAndName(final String cuit, final String name) {
        if (StringUtils.isEmpty(cuit) || !cuit.matches("^\\d{11}$")) {
            throw new IllegalArgumentException(CUIT_INVALID_ERROR);
        }
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException(NAME_INVALID_ERROR);
        }
    }
}
