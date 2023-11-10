package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemItemlocationMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemItemlocationMapDTO.class);
        ItemItemlocationMapDTO itemItemlocationMapDTO1 = new ItemItemlocationMapDTO();
        itemItemlocationMapDTO1.setItemItemlocationMapId(1L);
        ItemItemlocationMapDTO itemItemlocationMapDTO2 = new ItemItemlocationMapDTO();
        assertThat(itemItemlocationMapDTO1).isNotEqualTo(itemItemlocationMapDTO2);
        itemItemlocationMapDTO2.setItemItemlocationMapId(itemItemlocationMapDTO1.getItemItemlocationMapId());
        assertThat(itemItemlocationMapDTO1).isEqualTo(itemItemlocationMapDTO2);
        itemItemlocationMapDTO2.setItemItemlocationMapId(2L);
        assertThat(itemItemlocationMapDTO1).isNotEqualTo(itemItemlocationMapDTO2);
        itemItemlocationMapDTO1.setItemItemlocationMapId(null);
        assertThat(itemItemlocationMapDTO1).isNotEqualTo(itemItemlocationMapDTO2);
    }
}
