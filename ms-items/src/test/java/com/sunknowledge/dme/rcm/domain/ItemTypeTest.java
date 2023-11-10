package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemType.class);
        ItemType itemType1 = new ItemType();
        itemType1.setItemTypeId(1L);
        ItemType itemType2 = new ItemType();
        itemType2.setItemTypeId(itemType1.getItemTypeId());
        assertThat(itemType1).isEqualTo(itemType2);
        itemType2.setItemTypeId(2L);
        assertThat(itemType1).isNotEqualTo(itemType2);
        itemType1.setItemTypeId(null);
        assertThat(itemType1).isNotEqualTo(itemType2);
    }
}
