package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemLocationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemLocationDTO.class);
        ItemLocationDTO itemLocationDTO1 = new ItemLocationDTO();
        itemLocationDTO1.setItemLocationId(1L);
        ItemLocationDTO itemLocationDTO2 = new ItemLocationDTO();
        assertThat(itemLocationDTO1).isNotEqualTo(itemLocationDTO2);
        itemLocationDTO2.setItemLocationId(itemLocationDTO1.getItemLocationId());
        assertThat(itemLocationDTO1).isEqualTo(itemLocationDTO2);
        itemLocationDTO2.setItemLocationId(2L);
        assertThat(itemLocationDTO1).isNotEqualTo(itemLocationDTO2);
        itemLocationDTO1.setItemLocationId(null);
        assertThat(itemLocationDTO1).isNotEqualTo(itemLocationDTO2);
    }
}
