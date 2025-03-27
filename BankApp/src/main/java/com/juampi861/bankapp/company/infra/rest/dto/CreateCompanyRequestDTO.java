package com.juampi861.bankapp.company.infra.rest.dto;

import lombok.*;

@Getter
@Setter
public class CreateCompanyRequestDTO {
    private String cuit;
    private String name;
}