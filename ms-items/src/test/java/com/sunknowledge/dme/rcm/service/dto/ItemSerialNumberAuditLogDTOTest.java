package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemSerialNumberAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemSerialNumberAuditLogDTO.class);
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO1 = new ItemSerialNumberAuditLogDTO();
        itemSerialNumberAuditLogDTO1.setId(1L);
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO2 = new ItemSerialNumberAuditLogDTO();
        assertThat(itemSerialNumberAuditLogDTO1).isNotEqualTo(itemSerialNumberAuditLogDTO2);
        itemSerialNumberAuditLogDTO2.setId(itemSerialNumberAuditLogDTO1.getId());
        assertThat(itemSerialNumberAuditLogDTO1).isEqualTo(itemSerialNumberAuditLogDTO2);
        itemSerialNumberAuditLogDTO2.setId(2L);
        assertThat(itemSerialNumberAuditLogDTO1).isNotEqualTo(itemSerialNumberAuditLogDTO2);
        itemSerialNumberAuditLogDTO1.setId(null);
        assertThat(itemSerialNumberAuditLogDTO1).isNotEqualTo(itemSerialNumberAuditLogDTO2);
    }
}
