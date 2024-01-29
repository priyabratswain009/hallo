package com.sunknowledge.dme.rcm.service.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaxDocsSoItemDetailsCustomDTO {
    private Long itemId;
    private Long parId;
    private String parNo;
    private String soDetailsSaleType;
    private String primaryHcpcsCode;
}
