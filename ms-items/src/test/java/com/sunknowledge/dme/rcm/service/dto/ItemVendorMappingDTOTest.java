package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemVendorMappingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemVendorMappingDTO.class);
        ItemVendorMappingDTO itemVendorMappingDTO1 = new ItemVendorMappingDTO();
        itemVendorMappingDTO1.setItemVendorId(1L);
        ItemVendorMappingDTO itemVendorMappingDTO2 = new ItemVendorMappingDTO();
        assertThat(itemVendorMappingDTO1).isNotEqualTo(itemVendorMappingDTO2);
        itemVendorMappingDTO2.setItemVendorId(itemVendorMappingDTO1.getItemVendorId());
        assertThat(itemVendorMappingDTO1).isEqualTo(itemVendorMappingDTO2);
        itemVendorMappingDTO2.setItemVendorId(2L);
        assertThat(itemVendorMappingDTO1).isNotEqualTo(itemVendorMappingDTO2);
        itemVendorMappingDTO1.setItemVendorId(null);
        assertThat(itemVendorMappingDTO1).isNotEqualTo(itemVendorMappingDTO2);
    }
}
