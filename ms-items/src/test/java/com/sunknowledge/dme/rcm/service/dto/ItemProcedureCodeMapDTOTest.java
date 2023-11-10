package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemProcedureCodeMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemProcedureCodeMapDTO.class);
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO1 = new ItemProcedureCodeMapDTO();
        itemProcedureCodeMapDTO1.setItemProcedureCodeMapId(1L);
        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO2 = new ItemProcedureCodeMapDTO();
        assertThat(itemProcedureCodeMapDTO1).isNotEqualTo(itemProcedureCodeMapDTO2);
        itemProcedureCodeMapDTO2.setItemProcedureCodeMapId(itemProcedureCodeMapDTO1.getItemProcedureCodeMapId());
        assertThat(itemProcedureCodeMapDTO1).isEqualTo(itemProcedureCodeMapDTO2);
        itemProcedureCodeMapDTO2.setItemProcedureCodeMapId(2L);
        assertThat(itemProcedureCodeMapDTO1).isNotEqualTo(itemProcedureCodeMapDTO2);
        itemProcedureCodeMapDTO1.setItemProcedureCodeMapId(null);
        assertThat(itemProcedureCodeMapDTO1).isNotEqualTo(itemProcedureCodeMapDTO2);
    }
}
