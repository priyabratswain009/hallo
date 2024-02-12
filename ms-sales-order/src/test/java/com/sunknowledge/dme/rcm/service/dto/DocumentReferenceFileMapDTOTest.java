package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentReferenceFileMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentReferenceFileMapDTO.class);
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO1 = new DocumentReferenceFileMapDTO();
        documentReferenceFileMapDTO1.setDocumentReferenceFileMapId(1L);
        DocumentReferenceFileMapDTO documentReferenceFileMapDTO2 = new DocumentReferenceFileMapDTO();
        assertThat(documentReferenceFileMapDTO1).isNotEqualTo(documentReferenceFileMapDTO2);
        documentReferenceFileMapDTO2.setDocumentReferenceFileMapId(documentReferenceFileMapDTO1.getDocumentReferenceFileMapId());
        assertThat(documentReferenceFileMapDTO1).isEqualTo(documentReferenceFileMapDTO2);
        documentReferenceFileMapDTO2.setDocumentReferenceFileMapId(2L);
        assertThat(documentReferenceFileMapDTO1).isNotEqualTo(documentReferenceFileMapDTO2);
        documentReferenceFileMapDTO1.setDocumentReferenceFileMapId(null);
        assertThat(documentReferenceFileMapDTO1).isNotEqualTo(documentReferenceFileMapDTO2);
    }
}
