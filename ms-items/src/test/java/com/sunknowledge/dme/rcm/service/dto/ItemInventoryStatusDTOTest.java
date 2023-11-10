package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemInventoryStatusDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemInventoryStatusDTO.class);
        ItemInventoryStatusDTO itemInventoryStatusDTO1 = new ItemInventoryStatusDTO();
        itemInventoryStatusDTO1.setItemInventoryStatusId(1L);
        ItemInventoryStatusDTO itemInventoryStatusDTO2 = new ItemInventoryStatusDTO();
        assertThat(itemInventoryStatusDTO1).isNotEqualTo(itemInventoryStatusDTO2);
        itemInventoryStatusDTO2.setItemInventoryStatusId(itemInventoryStatusDTO1.getItemInventoryStatusId());
        assertThat(itemInventoryStatusDTO1).isEqualTo(itemInventoryStatusDTO2);
        itemInventoryStatusDTO2.setItemInventoryStatusId(2L);
        assertThat(itemInventoryStatusDTO1).isNotEqualTo(itemInventoryStatusDTO2);
        itemInventoryStatusDTO1.setItemInventoryStatusId(null);
        assertThat(itemInventoryStatusDTO1).isNotEqualTo(itemInventoryStatusDTO2);
    }
}
