package com.sunknowledge.dme.rcm.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PARResultDTO {
    private String parId;
    private String parNo;
    private LocalDate expirationDate;
    private LocalDate effectiveStartDate;
    private String logInStatus;
}
