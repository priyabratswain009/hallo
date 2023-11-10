package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemAssetNumberMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAssetNumberMap.class);
        ItemAssetNumberMap itemAssetNumberMap1 = new ItemAssetNumberMap();
        itemAssetNumberMap1.setItemAssetNumberId(1L);
        ItemAssetNumberMap itemAssetNumberMap2 = new ItemAssetNumberMap();
        itemAssetNumberMap2.setItemAssetNumberId(itemAssetNumberMap1.getItemAssetNumberId());
        assertThat(itemAssetNumberMap1).isEqualTo(itemAssetNumberMap2);
        itemAssetNumberMap2.setItemAssetNumberId(2L);
        assertThat(itemAssetNumberMap1).isNotEqualTo(itemAssetNumberMap2);
        itemAssetNumberMap1.setItemAssetNumberId(null);
        assertThat(itemAssetNumberMap1).isNotEqualTo(itemAssetNumberMap2);
    }
}
