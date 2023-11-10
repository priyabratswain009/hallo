package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChecklistDocumentReferenceMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChecklistDocumentReferenceMap.class);
        ChecklistDocumentReferenceMap checklistDocumentReferenceMap1 = new ChecklistDocumentReferenceMap();
        checklistDocumentReferenceMap1.setChecklistDocumentReferenceId(1L);
        ChecklistDocumentReferenceMap checklistDocumentReferenceMap2 = new ChecklistDocumentReferenceMap();
        checklistDocumentReferenceMap2.setChecklistDocumentReferenceId(checklistDocumentReferenceMap1.getChecklistDocumentReferenceId());
        assertThat(checklistDocumentReferenceMap1).isEqualTo(checklistDocumentReferenceMap2);
        checklistDocumentReferenceMap2.setChecklistDocumentReferenceId(2L);
        assertThat(checklistDocumentReferenceMap1).isNotEqualTo(checklistDocumentReferenceMap2);
        checklistDocumentReferenceMap1.setChecklistDocumentReferenceId(null);
        assertThat(checklistDocumentReferenceMap1).isNotEqualTo(checklistDocumentReferenceMap2);
    }
}
