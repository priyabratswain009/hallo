package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceDetailsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceDetailsAuditLogDTO.class);
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO1 = new PriceDetailsAuditLogDTO();
        priceDetailsAuditLogDTO1.setId(1L);
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO2 = new PriceDetailsAuditLogDTO();
        assertThat(priceDetailsAuditLogDTO1).isNotEqualTo(priceDetailsAuditLogDTO2);
        priceDetailsAuditLogDTO2.setId(priceDetailsAuditLogDTO1.getId());
        assertThat(priceDetailsAuditLogDTO1).isEqualTo(priceDetailsAuditLogDTO2);
        priceDetailsAuditLogDTO2.setId(2L);
        assertThat(priceDetailsAuditLogDTO1).isNotEqualTo(priceDetailsAuditLogDTO2);
        priceDetailsAuditLogDTO1.setId(null);
        assertThat(priceDetailsAuditLogDTO1).isNotEqualTo(priceDetailsAuditLogDTO2);
    }
}
