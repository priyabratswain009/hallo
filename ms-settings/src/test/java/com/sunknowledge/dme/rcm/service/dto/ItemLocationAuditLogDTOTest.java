package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemLocationAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemLocationAuditLogDTO.class);
        ItemLocationAuditLogDTO itemLocationAuditLogDTO1 = new ItemLocationAuditLogDTO();
        itemLocationAuditLogDTO1.setId(1L);
        ItemLocationAuditLogDTO itemLocationAuditLogDTO2 = new ItemLocationAuditLogDTO();
        assertThat(itemLocationAuditLogDTO1).isNotEqualTo(itemLocationAuditLogDTO2);
        itemLocationAuditLogDTO2.setId(itemLocationAuditLogDTO1.getId());
        assertThat(itemLocationAuditLogDTO1).isEqualTo(itemLocationAuditLogDTO2);
        itemLocationAuditLogDTO2.setId(2L);
        assertThat(itemLocationAuditLogDTO1).isNotEqualTo(itemLocationAuditLogDTO2);
        itemLocationAuditLogDTO1.setId(null);
        assertThat(itemLocationAuditLogDTO1).isNotEqualTo(itemLocationAuditLogDTO2);
    }
}
