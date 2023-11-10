package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaxZoneAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxZoneAuditLogDTO.class);
        TaxZoneAuditLogDTO taxZoneAuditLogDTO1 = new TaxZoneAuditLogDTO();
        taxZoneAuditLogDTO1.setId(1L);
        TaxZoneAuditLogDTO taxZoneAuditLogDTO2 = new TaxZoneAuditLogDTO();
        assertThat(taxZoneAuditLogDTO1).isNotEqualTo(taxZoneAuditLogDTO2);
        taxZoneAuditLogDTO2.setId(taxZoneAuditLogDTO1.getId());
        assertThat(taxZoneAuditLogDTO1).isEqualTo(taxZoneAuditLogDTO2);
        taxZoneAuditLogDTO2.setId(2L);
        assertThat(taxZoneAuditLogDTO1).isNotEqualTo(taxZoneAuditLogDTO2);
        taxZoneAuditLogDTO1.setId(null);
        assertThat(taxZoneAuditLogDTO1).isNotEqualTo(taxZoneAuditLogDTO2);
    }
}
