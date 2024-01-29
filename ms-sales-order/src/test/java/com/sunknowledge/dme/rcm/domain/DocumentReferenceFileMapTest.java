package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentReferenceFileMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentReferenceFileMap.class);
        DocumentReferenceFileMap documentReferenceFileMap1 = new DocumentReferenceFileMap();
        documentReferenceFileMap1.setDocumentReferenceFileMapId(1L);
        DocumentReferenceFileMap documentReferenceFileMap2 = new DocumentReferenceFileMap();
        documentReferenceFileMap2.setDocumentReferenceFileMapId(documentReferenceFileMap1.getDocumentReferenceFileMapId());
        assertThat(documentReferenceFileMap1).isEqualTo(documentReferenceFileMap2);
        documentReferenceFileMap2.setDocumentReferenceFileMapId(2L);
        assertThat(documentReferenceFileMap1).isNotEqualTo(documentReferenceFileMap2);
        documentReferenceFileMap1.setDocumentReferenceFileMapId(null);
        assertThat(documentReferenceFileMap1).isNotEqualTo(documentReferenceFileMap2);
    }
}
