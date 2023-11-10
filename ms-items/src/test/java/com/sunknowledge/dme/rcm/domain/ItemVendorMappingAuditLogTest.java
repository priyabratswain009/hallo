package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemVendorMappingAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemVendorMappingAuditLog.class);
        ItemVendorMappingAuditLog itemVendorMappingAuditLog1 = new ItemVendorMappingAuditLog();
        itemVendorMappingAuditLog1.setId(1L);
        ItemVendorMappingAuditLog itemVendorMappingAuditLog2 = new ItemVendorMappingAuditLog();
        itemVendorMappingAuditLog2.setId(itemVendorMappingAuditLog1.getId());
        assertThat(itemVendorMappingAuditLog1).isEqualTo(itemVendorMappingAuditLog2);
        itemVendorMappingAuditLog2.setId(2L);
        assertThat(itemVendorMappingAuditLog1).isNotEqualTo(itemVendorMappingAuditLog2);
        itemVendorMappingAuditLog1.setId(null);
        assertThat(itemVendorMappingAuditLog1).isNotEqualTo(itemVendorMappingAuditLog2);
    }
}
