package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemGroupTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemGroup.class);
        ItemGroup itemGroup1 = new ItemGroup();
        itemGroup1.setItemGroupId(1L);
        ItemGroup itemGroup2 = new ItemGroup();
        itemGroup2.setItemGroupId(itemGroup1.getItemGroupId());
        assertThat(itemGroup1).isEqualTo(itemGroup2);
        itemGroup2.setItemGroupId(2L);
        assertThat(itemGroup1).isNotEqualTo(itemGroup2);
        itemGroup1.setItemGroupId(null);
        assertThat(itemGroup1).isNotEqualTo(itemGroup2);
    }
}
