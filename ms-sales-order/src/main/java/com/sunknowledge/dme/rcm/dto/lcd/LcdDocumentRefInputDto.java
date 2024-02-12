package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LcdDocumentRefInputDto {
    private Long documentReferenceId;
    private String documentReference;
    private List<String> existingFileList;
    private String coverageCriteriaNotes;
    private Long soId;
    private Long itemGroupId;
    private Long coverageCriteriaId;
}
