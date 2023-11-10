package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemItemlocationMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemItemlocationMap.class);
        ItemItemlocationMap itemItemlocationMap1 = new ItemItemlocationMap();
        itemItemlocationMap1.setItemItemlocationMapId(1L);
        ItemItemlocationMap itemItemlocationMap2 = new ItemItemlocationMap();
        itemItemlocationMap2.setItemItemlocationMapId(itemItemlocationMap1.getItemItemlocationMapId());
        assertThat(itemItemlocationMap1).isEqualTo(itemItemlocationMap2);
        itemItemlocationMap2.setItemItemlocationMapId(2L);
        assertThat(itemItemlocationMap1).isNotEqualTo(itemItemlocationMap2);
        itemItemlocationMap1.setItemItemlocationMapId(null);
        assertThat(itemItemlocationMap1).isNotEqualTo(itemItemlocationMap2);
    }
}
