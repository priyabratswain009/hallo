package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HoldReasonMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoldReasonMasterAuditLogDTO.class);
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO1 = new HoldReasonMasterAuditLogDTO();
        holdReasonMasterAuditLogDTO1.setId(1L);
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO2 = new HoldReasonMasterAuditLogDTO();
        assertThat(holdReasonMasterAuditLogDTO1).isNotEqualTo(holdReasonMasterAuditLogDTO2);
        holdReasonMasterAuditLogDTO2.setId(holdReasonMasterAuditLogDTO1.getId());
        assertThat(holdReasonMasterAuditLogDTO1).isEqualTo(holdReasonMasterAuditLogDTO2);
        holdReasonMasterAuditLogDTO2.setId(2L);
        assertThat(holdReasonMasterAuditLogDTO1).isNotEqualTo(holdReasonMasterAuditLogDTO2);
        holdReasonMasterAuditLogDTO1.setId(null);
        assertThat(holdReasonMasterAuditLogDTO1).isNotEqualTo(holdReasonMasterAuditLogDTO2);
    }
}
