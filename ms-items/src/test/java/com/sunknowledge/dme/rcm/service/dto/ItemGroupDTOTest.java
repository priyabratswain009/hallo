package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemGroupDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemGroupDTO.class);
        ItemGroupDTO itemGroupDTO1 = new ItemGroupDTO();
        itemGroupDTO1.setItemGroupId(1L);
        ItemGroupDTO itemGroupDTO2 = new ItemGroupDTO();
        assertThat(itemGroupDTO1).isNotEqualTo(itemGroupDTO2);
        itemGroupDTO2.setItemGroupId(itemGroupDTO1.getItemGroupId());
        assertThat(itemGroupDTO1).isEqualTo(itemGroupDTO2);
        itemGroupDTO2.setItemGroupId(2L);
        assertThat(itemGroupDTO1).isNotEqualTo(itemGroupDTO2);
        itemGroupDTO1.setItemGroupId(null);
        assertThat(itemGroupDTO1).isNotEqualTo(itemGroupDTO2);
    }
}
