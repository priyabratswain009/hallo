package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemAssetNumberMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAssetNumberMapDTO.class);
        ItemAssetNumberMapDTO itemAssetNumberMapDTO1 = new ItemAssetNumberMapDTO();
        itemAssetNumberMapDTO1.setItemAssetNumberId(1L);
        ItemAssetNumberMapDTO itemAssetNumberMapDTO2 = new ItemAssetNumberMapDTO();
        assertThat(itemAssetNumberMapDTO1).isNotEqualTo(itemAssetNumberMapDTO2);
        itemAssetNumberMapDTO2.setItemAssetNumberId(itemAssetNumberMapDTO1.getItemAssetNumberId());
        assertThat(itemAssetNumberMapDTO1).isEqualTo(itemAssetNumberMapDTO2);
        itemAssetNumberMapDTO2.setItemAssetNumberId(2L);
        assertThat(itemAssetNumberMapDTO1).isNotEqualTo(itemAssetNumberMapDTO2);
        itemAssetNumberMapDTO1.setItemAssetNumberId(null);
        assertThat(itemAssetNumberMapDTO1).isNotEqualTo(itemAssetNumberMapDTO2);
    }
}
