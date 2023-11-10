package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemSerialNumberTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemSerialNumber.class);
        ItemSerialNumber itemSerialNumber1 = new ItemSerialNumber();
        itemSerialNumber1.setItemSerialNumberId(1L);
        ItemSerialNumber itemSerialNumber2 = new ItemSerialNumber();
        itemSerialNumber2.setItemSerialNumberId(itemSerialNumber1.getItemSerialNumberId());
        assertThat(itemSerialNumber1).isEqualTo(itemSerialNumber2);
        itemSerialNumber2.setItemSerialNumberId(2L);
        assertThat(itemSerialNumber1).isNotEqualTo(itemSerialNumber2);
        itemSerialNumber1.setItemSerialNumberId(null);
        assertThat(itemSerialNumber1).isNotEqualTo(itemSerialNumber2);
    }
}
