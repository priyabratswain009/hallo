package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemTypeDTO.class);
        ItemTypeDTO itemTypeDTO1 = new ItemTypeDTO();
        itemTypeDTO1.setItemTypeId(1L);
        ItemTypeDTO itemTypeDTO2 = new ItemTypeDTO();
        assertThat(itemTypeDTO1).isNotEqualTo(itemTypeDTO2);
        itemTypeDTO2.setItemTypeId(itemTypeDTO1.getItemTypeId());
        assertThat(itemTypeDTO1).isEqualTo(itemTypeDTO2);
        itemTypeDTO2.setItemTypeId(2L);
        assertThat(itemTypeDTO1).isNotEqualTo(itemTypeDTO2);
        itemTypeDTO1.setItemTypeId(null);
        assertThat(itemTypeDTO1).isNotEqualTo(itemTypeDTO2);
    }
}
