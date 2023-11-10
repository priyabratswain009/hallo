package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FunctionalAbilityAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunctionalAbilityAuditLogDTO.class);
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO1 = new FunctionalAbilityAuditLogDTO();
        functionalAbilityAuditLogDTO1.setId(1L);
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO2 = new FunctionalAbilityAuditLogDTO();
        assertThat(functionalAbilityAuditLogDTO1).isNotEqualTo(functionalAbilityAuditLogDTO2);
        functionalAbilityAuditLogDTO2.setId(functionalAbilityAuditLogDTO1.getId());
        assertThat(functionalAbilityAuditLogDTO1).isEqualTo(functionalAbilityAuditLogDTO2);
        functionalAbilityAuditLogDTO2.setId(2L);
        assertThat(functionalAbilityAuditLogDTO1).isNotEqualTo(functionalAbilityAuditLogDTO2);
        functionalAbilityAuditLogDTO1.setId(null);
        assertThat(functionalAbilityAuditLogDTO1).isNotEqualTo(functionalAbilityAuditLogDTO2);
    }
}
