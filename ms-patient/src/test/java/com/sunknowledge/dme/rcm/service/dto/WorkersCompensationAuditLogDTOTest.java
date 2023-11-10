package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkersCompensationAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkersCompensationAuditLogDTO.class);
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO1 = new WorkersCompensationAuditLogDTO();
        workersCompensationAuditLogDTO1.setId(1L);
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO2 = new WorkersCompensationAuditLogDTO();
        assertThat(workersCompensationAuditLogDTO1).isNotEqualTo(workersCompensationAuditLogDTO2);
        workersCompensationAuditLogDTO2.setId(workersCompensationAuditLogDTO1.getId());
        assertThat(workersCompensationAuditLogDTO1).isEqualTo(workersCompensationAuditLogDTO2);
        workersCompensationAuditLogDTO2.setId(2L);
        assertThat(workersCompensationAuditLogDTO1).isNotEqualTo(workersCompensationAuditLogDTO2);
        workersCompensationAuditLogDTO1.setId(null);
        assertThat(workersCompensationAuditLogDTO1).isNotEqualTo(workersCompensationAuditLogDTO2);
    }
}
