package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemLocationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemLocation.class);
        ItemLocation itemLocation1 = new ItemLocation();
        itemLocation1.setItemLocationId(1L);
        ItemLocation itemLocation2 = new ItemLocation();
        itemLocation2.setItemLocationId(itemLocation1.getItemLocationId());
        assertThat(itemLocation1).isEqualTo(itemLocation2);
        itemLocation2.setItemLocationId(2L);
        assertThat(itemLocation1).isNotEqualTo(itemLocation2);
        itemLocation1.setItemLocationId(null);
        assertThat(itemLocation1).isNotEqualTo(itemLocation2);
    }
}
