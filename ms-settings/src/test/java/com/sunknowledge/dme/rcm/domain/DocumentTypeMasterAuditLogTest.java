package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentTypeMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentTypeMasterAuditLog.class);
        DocumentTypeMasterAuditLog documentTypeMasterAuditLog1 = new DocumentTypeMasterAuditLog();
        documentTypeMasterAuditLog1.setId(1L);
        DocumentTypeMasterAuditLog documentTypeMasterAuditLog2 = new DocumentTypeMasterAuditLog();
        documentTypeMasterAuditLog2.setId(documentTypeMasterAuditLog1.getId());
        assertThat(documentTypeMasterAuditLog1).isEqualTo(documentTypeMasterAuditLog2);
        documentTypeMasterAuditLog2.setId(2L);
        assertThat(documentTypeMasterAuditLog1).isNotEqualTo(documentTypeMasterAuditLog2);
        documentTypeMasterAuditLog1.setId(null);
        assertThat(documentTypeMasterAuditLog1).isNotEqualTo(documentTypeMasterAuditLog2);
    }
}
