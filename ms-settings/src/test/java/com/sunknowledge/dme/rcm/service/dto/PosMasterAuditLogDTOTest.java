package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PosMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PosMasterAuditLogDTO.class);
        PosMasterAuditLogDTO posMasterAuditLogDTO1 = new PosMasterAuditLogDTO();
        posMasterAuditLogDTO1.setId(1L);
        PosMasterAuditLogDTO posMasterAuditLogDTO2 = new PosMasterAuditLogDTO();
        assertThat(posMasterAuditLogDTO1).isNotEqualTo(posMasterAuditLogDTO2);
        posMasterAuditLogDTO2.setId(posMasterAuditLogDTO1.getId());
        assertThat(posMasterAuditLogDTO1).isEqualTo(posMasterAuditLogDTO2);
        posMasterAuditLogDTO2.setId(2L);
        assertThat(posMasterAuditLogDTO1).isNotEqualTo(posMasterAuditLogDTO2);
        posMasterAuditLogDTO1.setId(null);
        assertThat(posMasterAuditLogDTO1).isNotEqualTo(posMasterAuditLogDTO2);
    }
}
