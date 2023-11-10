package com.sunknowledge.dme.rcm.dto.cmn;

import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmnResponseDetails {
    private CmnDTO cmnDTO;
    private CmnDocumentMasterDTO cmnDocumentMasterDTO;
}
