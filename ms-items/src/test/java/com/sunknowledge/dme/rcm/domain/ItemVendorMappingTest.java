package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemVendorMappingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemVendorMapping.class);
        ItemVendorMapping itemVendorMapping1 = new ItemVendorMapping();
        itemVendorMapping1.setItemVendorId(1L);
        ItemVendorMapping itemVendorMapping2 = new ItemVendorMapping();
        itemVendorMapping2.setItemVendorId(itemVendorMapping1.getItemVendorId());
        assertThat(itemVendorMapping1).isEqualTo(itemVendorMapping2);
        itemVendorMapping2.setItemVendorId(2L);
        assertThat(itemVendorMapping1).isNotEqualTo(itemVendorMapping2);
        itemVendorMapping1.setItemVendorId(null);
        assertThat(itemVendorMapping1).isNotEqualTo(itemVendorMapping2);
    }
}
