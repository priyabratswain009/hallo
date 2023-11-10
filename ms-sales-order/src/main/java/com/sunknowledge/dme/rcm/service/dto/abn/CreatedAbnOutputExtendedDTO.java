package com.sunknowledge.dme.rcm.service.dto.abn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedAbnOutputExtendedDTO {
    private String abnNo;
    private Long abnId;
    private UUID abnUuid;
    private LocalDate abnPrintDate;
    private String abnItem;
    private String abnProcCode;
    private String abnChargeAmt;
    private String abnModifier1;
    private String abnModifier2;
    private String abnUserResponse;
    private String abnReason;
    private String abnStatus;
}
