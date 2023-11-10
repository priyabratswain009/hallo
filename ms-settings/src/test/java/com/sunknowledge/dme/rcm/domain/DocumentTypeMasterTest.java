package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentTypeMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentTypeMaster.class);
        DocumentTypeMaster documentTypeMaster1 = new DocumentTypeMaster();
        documentTypeMaster1.setDocumentTypeId(1L);
        DocumentTypeMaster documentTypeMaster2 = new DocumentTypeMaster();
        documentTypeMaster2.setDocumentTypeId(documentTypeMaster1.getDocumentTypeId());
        assertThat(documentTypeMaster1).isEqualTo(documentTypeMaster2);
        documentTypeMaster2.setDocumentTypeId(2L);
        assertThat(documentTypeMaster1).isNotEqualTo(documentTypeMaster2);
        documentTypeMaster1.setDocumentTypeId(null);
        assertThat(documentTypeMaster1).isNotEqualTo(documentTypeMaster2);
    }
}
