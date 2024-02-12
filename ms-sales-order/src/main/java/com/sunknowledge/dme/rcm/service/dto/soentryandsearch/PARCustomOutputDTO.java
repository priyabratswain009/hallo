package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PARCustomOutputDTO {
    private String hcpcsCode;
    private String description;
    private Long itemQuantity;
    private LocalDate effectiveStartDate;
    private LocalDate expirationDate;
}
