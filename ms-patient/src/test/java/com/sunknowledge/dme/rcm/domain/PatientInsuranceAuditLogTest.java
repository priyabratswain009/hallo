package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientInsuranceAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInsuranceAuditLog.class);
        PatientInsuranceAuditLog patientInsuranceAuditLog1 = new PatientInsuranceAuditLog();
        patientInsuranceAuditLog1.setId(1L);
        PatientInsuranceAuditLog patientInsuranceAuditLog2 = new PatientInsuranceAuditLog();
        patientInsuranceAuditLog2.setId(patientInsuranceAuditLog1.getId());
        assertThat(patientInsuranceAuditLog1).isEqualTo(patientInsuranceAuditLog2);
        patientInsuranceAuditLog2.setId(2L);
        assertThat(patientInsuranceAuditLog1).isNotEqualTo(patientInsuranceAuditLog2);
        patientInsuranceAuditLog1.setId(null);
        assertThat(patientInsuranceAuditLog1).isNotEqualTo(patientInsuranceAuditLog2);
    }
}
