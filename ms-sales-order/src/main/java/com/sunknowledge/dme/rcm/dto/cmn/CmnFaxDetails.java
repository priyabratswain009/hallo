package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmnFaxDetails {
    private Long cmnId;
    private String outBoundFaxStatus;
    private String outBoundFaxNo;
    private String outBoundFaxDateTime;

    private String inBoundFaxStatus;
    private String inBoundFaxNo;
    private String inBoundFaxDateTime;
    private String returnDocumentName;
    private String cmnComments;
}
