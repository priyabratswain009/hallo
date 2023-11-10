package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemInventoryStatusAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemInventoryStatusAuditLog.class);
        ItemInventoryStatusAuditLog itemInventoryStatusAuditLog1 = new ItemInventoryStatusAuditLog();
        itemInventoryStatusAuditLog1.setId(1L);
        ItemInventoryStatusAuditLog itemInventoryStatusAuditLog2 = new ItemInventoryStatusAuditLog();
        itemInventoryStatusAuditLog2.setId(itemInventoryStatusAuditLog1.getId());
        assertThat(itemInventoryStatusAuditLog1).isEqualTo(itemInventoryStatusAuditLog2);
        itemInventoryStatusAuditLog2.setId(2L);
        assertThat(itemInventoryStatusAuditLog1).isNotEqualTo(itemInventoryStatusAuditLog2);
        itemInventoryStatusAuditLog1.setId(null);
        assertThat(itemInventoryStatusAuditLog1).isNotEqualTo(itemInventoryStatusAuditLog2);
    }
}
