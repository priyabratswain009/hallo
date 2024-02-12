package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LcdDocumentRefOutcomeDto {
    private List<Map<String, Object>> documentReferenceListMap;
    private String documentReference;
    private Long documentReferenceId;
    private List<Map<String, Object>> totalFileListMap;
    private List<String> existingFileList;
    private String coverageCriteriaNotes;
    private Long soId;
    private Long itemGroupId;
    private Long coverageCriteriaId;
}
