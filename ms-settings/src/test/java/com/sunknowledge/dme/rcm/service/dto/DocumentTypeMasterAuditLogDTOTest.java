package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentTypeMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentTypeMasterAuditLogDTO.class);
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO1 = new DocumentTypeMasterAuditLogDTO();
        documentTypeMasterAuditLogDTO1.setId(1L);
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO2 = new DocumentTypeMasterAuditLogDTO();
        assertThat(documentTypeMasterAuditLogDTO1).isNotEqualTo(documentTypeMasterAuditLogDTO2);
        documentTypeMasterAuditLogDTO2.setId(documentTypeMasterAuditLogDTO1.getId());
        assertThat(documentTypeMasterAuditLogDTO1).isEqualTo(documentTypeMasterAuditLogDTO2);
        documentTypeMasterAuditLogDTO2.setId(2L);
        assertThat(documentTypeMasterAuditLogDTO1).isNotEqualTo(documentTypeMasterAuditLogDTO2);
        documentTypeMasterAuditLogDTO1.setId(null);
        assertThat(documentTypeMasterAuditLogDTO1).isNotEqualTo(documentTypeMasterAuditLogDTO2);
    }
}
