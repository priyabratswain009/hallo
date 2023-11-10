package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemGroupAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemGroupAuditLogDTO.class);
        ItemGroupAuditLogDTO itemGroupAuditLogDTO1 = new ItemGroupAuditLogDTO();
        itemGroupAuditLogDTO1.setId(1L);
        ItemGroupAuditLogDTO itemGroupAuditLogDTO2 = new ItemGroupAuditLogDTO();
        assertThat(itemGroupAuditLogDTO1).isNotEqualTo(itemGroupAuditLogDTO2);
        itemGroupAuditLogDTO2.setId(itemGroupAuditLogDTO1.getId());
        assertThat(itemGroupAuditLogDTO1).isEqualTo(itemGroupAuditLogDTO2);
        itemGroupAuditLogDTO2.setId(2L);
        assertThat(itemGroupAuditLogDTO1).isNotEqualTo(itemGroupAuditLogDTO2);
        itemGroupAuditLogDTO1.setId(null);
        assertThat(itemGroupAuditLogDTO1).isNotEqualTo(itemGroupAuditLogDTO2);
    }
}
