package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ManufacturerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManufacturerDTO.class);
        ManufacturerDTO manufacturerDTO1 = new ManufacturerDTO();
        manufacturerDTO1.setManufacturerId(1L);
        ManufacturerDTO manufacturerDTO2 = new ManufacturerDTO();
        assertThat(manufacturerDTO1).isNotEqualTo(manufacturerDTO2);
        manufacturerDTO2.setManufacturerId(manufacturerDTO1.getManufacturerId());
        assertThat(manufacturerDTO1).isEqualTo(manufacturerDTO2);
        manufacturerDTO2.setManufacturerId(2L);
        assertThat(manufacturerDTO1).isNotEqualTo(manufacturerDTO2);
        manufacturerDTO1.setManufacturerId(null);
        assertThat(manufacturerDTO1).isNotEqualTo(manufacturerDTO2);
    }
}
