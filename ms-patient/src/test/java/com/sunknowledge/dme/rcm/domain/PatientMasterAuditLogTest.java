package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientMasterAuditLog.class);
        PatientMasterAuditLog patientMasterAuditLog1 = new PatientMasterAuditLog();
        patientMasterAuditLog1.setId(1L);
        PatientMasterAuditLog patientMasterAuditLog2 = new PatientMasterAuditLog();
        patientMasterAuditLog2.setId(patientMasterAuditLog1.getId());
        assertThat(patientMasterAuditLog1).isEqualTo(patientMasterAuditLog2);
        patientMasterAuditLog2.setId(2L);
        assertThat(patientMasterAuditLog1).isNotEqualTo(patientMasterAuditLog2);
        patientMasterAuditLog1.setId(null);
        assertThat(patientMasterAuditLog1).isNotEqualTo(patientMasterAuditLog2);
    }
}
