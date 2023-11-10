package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceMasterAuditLogDTO.class);
        PriceMasterAuditLogDTO priceMasterAuditLogDTO1 = new PriceMasterAuditLogDTO();
        priceMasterAuditLogDTO1.setId(1L);
        PriceMasterAuditLogDTO priceMasterAuditLogDTO2 = new PriceMasterAuditLogDTO();
        assertThat(priceMasterAuditLogDTO1).isNotEqualTo(priceMasterAuditLogDTO2);
        priceMasterAuditLogDTO2.setId(priceMasterAuditLogDTO1.getId());
        assertThat(priceMasterAuditLogDTO1).isEqualTo(priceMasterAuditLogDTO2);
        priceMasterAuditLogDTO2.setId(2L);
        assertThat(priceMasterAuditLogDTO1).isNotEqualTo(priceMasterAuditLogDTO2);
        priceMasterAuditLogDTO1.setId(null);
        assertThat(priceMasterAuditLogDTO1).isNotEqualTo(priceMasterAuditLogDTO2);
    }
}
