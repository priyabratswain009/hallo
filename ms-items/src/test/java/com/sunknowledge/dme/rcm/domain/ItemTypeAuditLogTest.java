package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemTypeAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemTypeAuditLog.class);
        ItemTypeAuditLog itemTypeAuditLog1 = new ItemTypeAuditLog();
        itemTypeAuditLog1.setId(1L);
        ItemTypeAuditLog itemTypeAuditLog2 = new ItemTypeAuditLog();
        itemTypeAuditLog2.setId(itemTypeAuditLog1.getId());
        assertThat(itemTypeAuditLog1).isEqualTo(itemTypeAuditLog2);
        itemTypeAuditLog2.setId(2L);
        assertThat(itemTypeAuditLog1).isNotEqualTo(itemTypeAuditLog2);
        itemTypeAuditLog1.setId(null);
        assertThat(itemTypeAuditLog1).isNotEqualTo(itemTypeAuditLog2);
    }
}
