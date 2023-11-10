package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemProcedureCodeMapAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemProcedureCodeMapAuditLogDTO.class);
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO1 = new ItemProcedureCodeMapAuditLogDTO();
        itemProcedureCodeMapAuditLogDTO1.setId(1L);
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO2 = new ItemProcedureCodeMapAuditLogDTO();
        assertThat(itemProcedureCodeMapAuditLogDTO1).isNotEqualTo(itemProcedureCodeMapAuditLogDTO2);
        itemProcedureCodeMapAuditLogDTO2.setId(itemProcedureCodeMapAuditLogDTO1.getId());
        assertThat(itemProcedureCodeMapAuditLogDTO1).isEqualTo(itemProcedureCodeMapAuditLogDTO2);
        itemProcedureCodeMapAuditLogDTO2.setId(2L);
        assertThat(itemProcedureCodeMapAuditLogDTO1).isNotEqualTo(itemProcedureCodeMapAuditLogDTO2);
        itemProcedureCodeMapAuditLogDTO1.setId(null);
        assertThat(itemProcedureCodeMapAuditLogDTO1).isNotEqualTo(itemProcedureCodeMapAuditLogDTO2);
    }
}
