package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChecklistDocumentReferenceMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChecklistDocumentReferenceMapDTO.class);
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO1 = new ChecklistDocumentReferenceMapDTO();
        checklistDocumentReferenceMapDTO1.setChecklistDocumentReferenceId(1L);
        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO2 = new ChecklistDocumentReferenceMapDTO();
        assertThat(checklistDocumentReferenceMapDTO1).isNotEqualTo(checklistDocumentReferenceMapDTO2);
        checklistDocumentReferenceMapDTO2.setChecklistDocumentReferenceId(
            checklistDocumentReferenceMapDTO1.getChecklistDocumentReferenceId()
        );
        assertThat(checklistDocumentReferenceMapDTO1).isEqualTo(checklistDocumentReferenceMapDTO2);
        checklistDocumentReferenceMapDTO2.setChecklistDocumentReferenceId(2L);
        assertThat(checklistDocumentReferenceMapDTO1).isNotEqualTo(checklistDocumentReferenceMapDTO2);
        checklistDocumentReferenceMapDTO1.setChecklistDocumentReferenceId(null);
        assertThat(checklistDocumentReferenceMapDTO1).isNotEqualTo(checklistDocumentReferenceMapDTO2);
    }
}
