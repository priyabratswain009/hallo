package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkersCompensationAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkersCompensationAuditLog.class);
        WorkersCompensationAuditLog workersCompensationAuditLog1 = new WorkersCompensationAuditLog();
        workersCompensationAuditLog1.setId(1L);
        WorkersCompensationAuditLog workersCompensationAuditLog2 = new WorkersCompensationAuditLog();
        workersCompensationAuditLog2.setId(workersCompensationAuditLog1.getId());
        assertThat(workersCompensationAuditLog1).isEqualTo(workersCompensationAuditLog2);
        workersCompensationAuditLog2.setId(2L);
        assertThat(workersCompensationAuditLog1).isNotEqualTo(workersCompensationAuditLog2);
        workersCompensationAuditLog1.setId(null);
        assertThat(workersCompensationAuditLog1).isNotEqualTo(workersCompensationAuditLog2);
    }
}
