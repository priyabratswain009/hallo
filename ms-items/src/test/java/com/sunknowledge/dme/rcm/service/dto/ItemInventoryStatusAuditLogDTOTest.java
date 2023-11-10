package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemInventoryStatusAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemInventoryStatusAuditLogDTO.class);
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO1 = new ItemInventoryStatusAuditLogDTO();
        itemInventoryStatusAuditLogDTO1.setId(1L);
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO2 = new ItemInventoryStatusAuditLogDTO();
        assertThat(itemInventoryStatusAuditLogDTO1).isNotEqualTo(itemInventoryStatusAuditLogDTO2);
        itemInventoryStatusAuditLogDTO2.setId(itemInventoryStatusAuditLogDTO1.getId());
        assertThat(itemInventoryStatusAuditLogDTO1).isEqualTo(itemInventoryStatusAuditLogDTO2);
        itemInventoryStatusAuditLogDTO2.setId(2L);
        assertThat(itemInventoryStatusAuditLogDTO1).isNotEqualTo(itemInventoryStatusAuditLogDTO2);
        itemInventoryStatusAuditLogDTO1.setId(null);
        assertThat(itemInventoryStatusAuditLogDTO1).isNotEqualTo(itemInventoryStatusAuditLogDTO2);
    }
}
