package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StopReasonMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StopReasonMasterAuditLogDTO.class);
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO1 = new StopReasonMasterAuditLogDTO();
        stopReasonMasterAuditLogDTO1.setId(1L);
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO2 = new StopReasonMasterAuditLogDTO();
        assertThat(stopReasonMasterAuditLogDTO1).isNotEqualTo(stopReasonMasterAuditLogDTO2);
        stopReasonMasterAuditLogDTO2.setId(stopReasonMasterAuditLogDTO1.getId());
        assertThat(stopReasonMasterAuditLogDTO1).isEqualTo(stopReasonMasterAuditLogDTO2);
        stopReasonMasterAuditLogDTO2.setId(2L);
        assertThat(stopReasonMasterAuditLogDTO1).isNotEqualTo(stopReasonMasterAuditLogDTO2);
        stopReasonMasterAuditLogDTO1.setId(null);
        assertThat(stopReasonMasterAuditLogDTO1).isNotEqualTo(stopReasonMasterAuditLogDTO2);
    }
}
