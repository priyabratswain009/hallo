package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentTypeMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentTypeMasterDTO.class);
        DocumentTypeMasterDTO documentTypeMasterDTO1 = new DocumentTypeMasterDTO();
        documentTypeMasterDTO1.setDocumentTypeId(1L);
        DocumentTypeMasterDTO documentTypeMasterDTO2 = new DocumentTypeMasterDTO();
        assertThat(documentTypeMasterDTO1).isNotEqualTo(documentTypeMasterDTO2);
        documentTypeMasterDTO2.setDocumentTypeId(documentTypeMasterDTO1.getDocumentTypeId());
        assertThat(documentTypeMasterDTO1).isEqualTo(documentTypeMasterDTO2);
        documentTypeMasterDTO2.setDocumentTypeId(2L);
        assertThat(documentTypeMasterDTO1).isNotEqualTo(documentTypeMasterDTO2);
        documentTypeMasterDTO1.setDocumentTypeId(null);
        assertThat(documentTypeMasterDTO1).isNotEqualTo(documentTypeMasterDTO2);
    }
}
