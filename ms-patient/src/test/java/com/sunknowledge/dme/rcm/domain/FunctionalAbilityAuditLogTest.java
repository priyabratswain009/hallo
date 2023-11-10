package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FunctionalAbilityAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunctionalAbilityAuditLog.class);
        FunctionalAbilityAuditLog functionalAbilityAuditLog1 = new FunctionalAbilityAuditLog();
        functionalAbilityAuditLog1.setId(1L);
        FunctionalAbilityAuditLog functionalAbilityAuditLog2 = new FunctionalAbilityAuditLog();
        functionalAbilityAuditLog2.setId(functionalAbilityAuditLog1.getId());
        assertThat(functionalAbilityAuditLog1).isEqualTo(functionalAbilityAuditLog2);
        functionalAbilityAuditLog2.setId(2L);
        assertThat(functionalAbilityAuditLog1).isNotEqualTo(functionalAbilityAuditLog2);
        functionalAbilityAuditLog1.setId(null);
        assertThat(functionalAbilityAuditLog1).isNotEqualTo(functionalAbilityAuditLog2);
    }
}
