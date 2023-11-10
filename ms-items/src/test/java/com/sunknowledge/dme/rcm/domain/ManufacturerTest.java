package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ManufacturerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manufacturer.class);
        Manufacturer manufacturer1 = new Manufacturer();
        manufacturer1.setManufacturerId(1L);
        Manufacturer manufacturer2 = new Manufacturer();
        manufacturer2.setManufacturerId(manufacturer1.getManufacturerId());
        assertThat(manufacturer1).isEqualTo(manufacturer2);
        manufacturer2.setManufacturerId(2L);
        assertThat(manufacturer1).isNotEqualTo(manufacturer2);
        manufacturer1.setManufacturerId(null);
        assertThat(manufacturer1).isNotEqualTo(manufacturer2);
    }
}
