package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentType.class);
        DocumentType documentType1 = new DocumentType();
        documentType1.setDocumentTypeId(1L);
        DocumentType documentType2 = new DocumentType();
        documentType2.setDocumentTypeId(documentType1.getDocumentTypeId());
        assertThat(documentType1).isEqualTo(documentType2);
        documentType2.setDocumentTypeId(2L);
        assertThat(documentType1).isNotEqualTo(documentType2);
        documentType1.setDocumentTypeId(null);
        assertThat(documentType1).isNotEqualTo(documentType2);
    }
}
