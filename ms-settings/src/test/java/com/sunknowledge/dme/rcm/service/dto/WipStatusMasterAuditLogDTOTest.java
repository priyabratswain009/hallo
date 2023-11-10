package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WipStatusMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WipStatusMasterAuditLogDTO.class);
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO1 = new WipStatusMasterAuditLogDTO();
        wipStatusMasterAuditLogDTO1.setId(1L);
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO2 = new WipStatusMasterAuditLogDTO();
        assertThat(wipStatusMasterAuditLogDTO1).isNotEqualTo(wipStatusMasterAuditLogDTO2);
        wipStatusMasterAuditLogDTO2.setId(wipStatusMasterAuditLogDTO1.getId());
        assertThat(wipStatusMasterAuditLogDTO1).isEqualTo(wipStatusMasterAuditLogDTO2);
        wipStatusMasterAuditLogDTO2.setId(2L);
        assertThat(wipStatusMasterAuditLogDTO1).isNotEqualTo(wipStatusMasterAuditLogDTO2);
        wipStatusMasterAuditLogDTO1.setId(null);
        assertThat(wipStatusMasterAuditLogDTO1).isNotEqualTo(wipStatusMasterAuditLogDTO2);
    }
}
