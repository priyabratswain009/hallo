package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemMaster.class);
        ItemMaster itemMaster1 = new ItemMaster();
        itemMaster1.setItemId(1L);
        ItemMaster itemMaster2 = new ItemMaster();
        itemMaster2.setItemId(itemMaster1.getItemId());
        assertThat(itemMaster1).isEqualTo(itemMaster2);
        itemMaster2.setItemId(2L);
        assertThat(itemMaster1).isNotEqualTo(itemMaster2);
        itemMaster1.setItemId(null);
        assertThat(itemMaster1).isNotEqualTo(itemMaster2);
    }
}
