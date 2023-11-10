package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemTypeAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemTypeAuditLogDTO.class);
        ItemTypeAuditLogDTO itemTypeAuditLogDTO1 = new ItemTypeAuditLogDTO();
        itemTypeAuditLogDTO1.setId(1L);
        ItemTypeAuditLogDTO itemTypeAuditLogDTO2 = new ItemTypeAuditLogDTO();
        assertThat(itemTypeAuditLogDTO1).isNotEqualTo(itemTypeAuditLogDTO2);
        itemTypeAuditLogDTO2.setId(itemTypeAuditLogDTO1.getId());
        assertThat(itemTypeAuditLogDTO1).isEqualTo(itemTypeAuditLogDTO2);
        itemTypeAuditLogDTO2.setId(2L);
        assertThat(itemTypeAuditLogDTO1).isNotEqualTo(itemTypeAuditLogDTO2);
        itemTypeAuditLogDTO1.setId(null);
        assertThat(itemTypeAuditLogDTO1).isNotEqualTo(itemTypeAuditLogDTO2);
    }
}
