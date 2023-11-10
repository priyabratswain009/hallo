package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentReturnType {
    private Boolean documentType;
    private String qrcode;
}
