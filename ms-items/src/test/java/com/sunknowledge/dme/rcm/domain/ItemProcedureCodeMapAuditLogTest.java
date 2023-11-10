package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemProcedureCodeMapAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemProcedureCodeMapAuditLog.class);
        ItemProcedureCodeMapAuditLog itemProcedureCodeMapAuditLog1 = new ItemProcedureCodeMapAuditLog();
        itemProcedureCodeMapAuditLog1.setId(1L);
        ItemProcedureCodeMapAuditLog itemProcedureCodeMapAuditLog2 = new ItemProcedureCodeMapAuditLog();
        itemProcedureCodeMapAuditLog2.setId(itemProcedureCodeMapAuditLog1.getId());
        assertThat(itemProcedureCodeMapAuditLog1).isEqualTo(itemProcedureCodeMapAuditLog2);
        itemProcedureCodeMapAuditLog2.setId(2L);
        assertThat(itemProcedureCodeMapAuditLog1).isNotEqualTo(itemProcedureCodeMapAuditLog2);
        itemProcedureCodeMapAuditLog1.setId(null);
        assertThat(itemProcedureCodeMapAuditLog1).isNotEqualTo(itemProcedureCodeMapAuditLog2);
    }
}
