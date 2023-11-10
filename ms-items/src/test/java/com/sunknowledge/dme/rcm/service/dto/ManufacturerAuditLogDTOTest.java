package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ManufacturerAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManufacturerAuditLogDTO.class);
        ManufacturerAuditLogDTO manufacturerAuditLogDTO1 = new ManufacturerAuditLogDTO();
        manufacturerAuditLogDTO1.setId(1L);
        ManufacturerAuditLogDTO manufacturerAuditLogDTO2 = new ManufacturerAuditLogDTO();
        assertThat(manufacturerAuditLogDTO1).isNotEqualTo(manufacturerAuditLogDTO2);
        manufacturerAuditLogDTO2.setId(manufacturerAuditLogDTO1.getId());
        assertThat(manufacturerAuditLogDTO1).isEqualTo(manufacturerAuditLogDTO2);
        manufacturerAuditLogDTO2.setId(2L);
        assertThat(manufacturerAuditLogDTO1).isNotEqualTo(manufacturerAuditLogDTO2);
        manufacturerAuditLogDTO1.setId(null);
        assertThat(manufacturerAuditLogDTO1).isNotEqualTo(manufacturerAuditLogDTO2);
    }
}
