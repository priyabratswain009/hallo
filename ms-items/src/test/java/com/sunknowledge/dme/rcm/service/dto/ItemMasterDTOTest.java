package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemMasterDTO.class);
        ItemMasterDTO itemMasterDTO1 = new ItemMasterDTO();
        itemMasterDTO1.setItemId(1L);
        ItemMasterDTO itemMasterDTO2 = new ItemMasterDTO();
        assertThat(itemMasterDTO1).isNotEqualTo(itemMasterDTO2);
        itemMasterDTO2.setItemId(itemMasterDTO1.getItemId());
        assertThat(itemMasterDTO1).isEqualTo(itemMasterDTO2);
        itemMasterDTO2.setItemId(2L);
        assertThat(itemMasterDTO1).isNotEqualTo(itemMasterDTO2);
        itemMasterDTO1.setItemId(null);
        assertThat(itemMasterDTO1).isNotEqualTo(itemMasterDTO2);
    }
}
