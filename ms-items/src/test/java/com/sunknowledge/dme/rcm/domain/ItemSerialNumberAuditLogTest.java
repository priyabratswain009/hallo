package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemSerialNumberAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemSerialNumberAuditLog.class);
        ItemSerialNumberAuditLog itemSerialNumberAuditLog1 = new ItemSerialNumberAuditLog();
        itemSerialNumberAuditLog1.setId(1L);
        ItemSerialNumberAuditLog itemSerialNumberAuditLog2 = new ItemSerialNumberAuditLog();
        itemSerialNumberAuditLog2.setId(itemSerialNumberAuditLog1.getId());
        assertThat(itemSerialNumberAuditLog1).isEqualTo(itemSerialNumberAuditLog2);
        itemSerialNumberAuditLog2.setId(2L);
        assertThat(itemSerialNumberAuditLog1).isNotEqualTo(itemSerialNumberAuditLog2);
        itemSerialNumberAuditLog1.setId(null);
        assertThat(itemSerialNumberAuditLog1).isNotEqualTo(itemSerialNumberAuditLog2);
    }
}
