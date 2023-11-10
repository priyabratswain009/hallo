package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemVendorMappingAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemVendorMappingAuditLogDTO.class);
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO1 = new ItemVendorMappingAuditLogDTO();
        itemVendorMappingAuditLogDTO1.setId(1L);
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO2 = new ItemVendorMappingAuditLogDTO();
        assertThat(itemVendorMappingAuditLogDTO1).isNotEqualTo(itemVendorMappingAuditLogDTO2);
        itemVendorMappingAuditLogDTO2.setId(itemVendorMappingAuditLogDTO1.getId());
        assertThat(itemVendorMappingAuditLogDTO1).isEqualTo(itemVendorMappingAuditLogDTO2);
        itemVendorMappingAuditLogDTO2.setId(2L);
        assertThat(itemVendorMappingAuditLogDTO1).isNotEqualTo(itemVendorMappingAuditLogDTO2);
        itemVendorMappingAuditLogDTO1.setId(null);
        assertThat(itemVendorMappingAuditLogDTO1).isNotEqualTo(itemVendorMappingAuditLogDTO2);
    }
}
