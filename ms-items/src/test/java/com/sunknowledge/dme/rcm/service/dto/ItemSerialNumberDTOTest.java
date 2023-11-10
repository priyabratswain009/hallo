package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemSerialNumberDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemSerialNumberDTO.class);
        ItemSerialNumberDTO itemSerialNumberDTO1 = new ItemSerialNumberDTO();
        itemSerialNumberDTO1.setItemSerialNumberId(1L);
        ItemSerialNumberDTO itemSerialNumberDTO2 = new ItemSerialNumberDTO();
        assertThat(itemSerialNumberDTO1).isNotEqualTo(itemSerialNumberDTO2);
        itemSerialNumberDTO2.setItemSerialNumberId(itemSerialNumberDTO1.getItemSerialNumberId());
        assertThat(itemSerialNumberDTO1).isEqualTo(itemSerialNumberDTO2);
        itemSerialNumberDTO2.setItemSerialNumberId(2L);
        assertThat(itemSerialNumberDTO1).isNotEqualTo(itemSerialNumberDTO2);
        itemSerialNumberDTO1.setItemSerialNumberId(null);
        assertThat(itemSerialNumberDTO1).isNotEqualTo(itemSerialNumberDTO2);
    }
}
