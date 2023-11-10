package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemMasterAuditLog.class);
        ItemMasterAuditLog itemMasterAuditLog1 = new ItemMasterAuditLog();
        itemMasterAuditLog1.setId(1L);
        ItemMasterAuditLog itemMasterAuditLog2 = new ItemMasterAuditLog();
        itemMasterAuditLog2.setId(itemMasterAuditLog1.getId());
        assertThat(itemMasterAuditLog1).isEqualTo(itemMasterAuditLog2);
        itemMasterAuditLog2.setId(2L);
        assertThat(itemMasterAuditLog1).isNotEqualTo(itemMasterAuditLog2);
        itemMasterAuditLog1.setId(null);
        assertThat(itemMasterAuditLog1).isNotEqualTo(itemMasterAuditLog2);
    }
}
