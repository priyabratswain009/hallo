package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientInsVerifStatAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInsVerifStatAuditLog.class);
        PatientInsVerifStatAuditLog patientInsVerifStatAuditLog1 = new PatientInsVerifStatAuditLog();
        patientInsVerifStatAuditLog1.setId(1L);
        PatientInsVerifStatAuditLog patientInsVerifStatAuditLog2 = new PatientInsVerifStatAuditLog();
        patientInsVerifStatAuditLog2.setId(patientInsVerifStatAuditLog1.getId());
        assertThat(patientInsVerifStatAuditLog1).isEqualTo(patientInsVerifStatAuditLog2);
        patientInsVerifStatAuditLog2.setId(2L);
        assertThat(patientInsVerifStatAuditLog1).isNotEqualTo(patientInsVerifStatAuditLog2);
        patientInsVerifStatAuditLog1.setId(null);
        assertThat(patientInsVerifStatAuditLog1).isNotEqualTo(patientInsVerifStatAuditLog2);
    }
}
