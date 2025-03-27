package com.juampi861.bankapp.company.infra.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class CompanyDTO {
    private String cuit;
    private String name;
    @JsonFormat(pattern = "dd-MM-yy:HH-mm")
    private LocalDateTime createdTime;
}
