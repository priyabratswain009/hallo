package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentsBySoIdOutputDTO {
    private Long soId;
    private String documentType;
    private String documentNo;
    private String documentName;
}
