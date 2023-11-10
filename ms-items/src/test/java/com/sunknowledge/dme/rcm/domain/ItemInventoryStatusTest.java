package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemInventoryStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemInventoryStatus.class);
        ItemInventoryStatus itemInventoryStatus1 = new ItemInventoryStatus();
        itemInventoryStatus1.setItemInventoryStatusId(1L);
        ItemInventoryStatus itemInventoryStatus2 = new ItemInventoryStatus();
        itemInventoryStatus2.setItemInventoryStatusId(itemInventoryStatus1.getItemInventoryStatusId());
        assertThat(itemInventoryStatus1).isEqualTo(itemInventoryStatus2);
        itemInventoryStatus2.setItemInventoryStatusId(2L);
        assertThat(itemInventoryStatus1).isNotEqualTo(itemInventoryStatus2);
        itemInventoryStatus1.setItemInventoryStatusId(null);
        assertThat(itemInventoryStatus1).isNotEqualTo(itemInventoryStatus2);
    }
}
