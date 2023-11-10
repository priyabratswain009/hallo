package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemProcedureCodeMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemProcedureCodeMap.class);
        ItemProcedureCodeMap itemProcedureCodeMap1 = new ItemProcedureCodeMap();
        itemProcedureCodeMap1.setItemProcedureCodeMapId(1L);
        ItemProcedureCodeMap itemProcedureCodeMap2 = new ItemProcedureCodeMap();
        itemProcedureCodeMap2.setItemProcedureCodeMapId(itemProcedureCodeMap1.getItemProcedureCodeMapId());
        assertThat(itemProcedureCodeMap1).isEqualTo(itemProcedureCodeMap2);
        itemProcedureCodeMap2.setItemProcedureCodeMapId(2L);
        assertThat(itemProcedureCodeMap1).isNotEqualTo(itemProcedureCodeMap2);
        itemProcedureCodeMap1.setItemProcedureCodeMapId(null);
        assertThat(itemProcedureCodeMap1).isNotEqualTo(itemProcedureCodeMap2);
    }
}
