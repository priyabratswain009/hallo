package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemGroupAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemGroupAuditLog.class);
        ItemGroupAuditLog itemGroupAuditLog1 = new ItemGroupAuditLog();
        itemGroupAuditLog1.setId(1L);
        ItemGroupAuditLog itemGroupAuditLog2 = new ItemGroupAuditLog();
        itemGroupAuditLog2.setId(itemGroupAuditLog1.getId());
        assertThat(itemGroupAuditLog1).isEqualTo(itemGroupAuditLog2);
        itemGroupAuditLog2.setId(2L);
        assertThat(itemGroupAuditLog1).isNotEqualTo(itemGroupAuditLog2);
        itemGroupAuditLog1.setId(null);
        assertThat(itemGroupAuditLog1).isNotEqualTo(itemGroupAuditLog2);
    }
}
