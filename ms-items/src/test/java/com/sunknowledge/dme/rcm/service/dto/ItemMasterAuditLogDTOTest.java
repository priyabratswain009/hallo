package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemMasterAuditLogDTO.class);
        ItemMasterAuditLogDTO itemMasterAuditLogDTO1 = new ItemMasterAuditLogDTO();
        itemMasterAuditLogDTO1.setId(1L);
        ItemMasterAuditLogDTO itemMasterAuditLogDTO2 = new ItemMasterAuditLogDTO();
        assertThat(itemMasterAuditLogDTO1).isNotEqualTo(itemMasterAuditLogDTO2);
        itemMasterAuditLogDTO2.setId(itemMasterAuditLogDTO1.getId());
        assertThat(itemMasterAuditLogDTO1).isEqualTo(itemMasterAuditLogDTO2);
        itemMasterAuditLogDTO2.setId(2L);
        assertThat(itemMasterAuditLogDTO1).isNotEqualTo(itemMasterAuditLogDTO2);
        itemMasterAuditLogDTO1.setId(null);
        assertThat(itemMasterAuditLogDTO1).isNotEqualTo(itemMasterAuditLogDTO2);
    }
}
