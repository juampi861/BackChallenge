package com.juampi861.bankapp.company.infra.rest.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class CreateCompanyRequestDTO {
    @Pattern(regexp = "^\\d{11}$", message = "Invalid CUIT. Should contain 11 digits")
    private String cuit;
    private String name;
}