package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PickupExchangeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickupExchangeDTO.class);
        PickupExchangeDTO pickupExchangeDTO1 = new PickupExchangeDTO();
        pickupExchangeDTO1.setPickupExchangeId(1L);
        PickupExchangeDTO pickupExchangeDTO2 = new PickupExchangeDTO();
        assertThat(pickupExchangeDTO1).isNotEqualTo(pickupExchangeDTO2);
        pickupExchangeDTO2.setPickupExchangeId(pickupExchangeDTO1.getPickupExchangeId());
        assertThat(pickupExchangeDTO1).isEqualTo(pickupExchangeDTO2);
        pickupExchangeDTO2.setPickupExchangeId(2L);
        assertThat(pickupExchangeDTO1).isNotEqualTo(pickupExchangeDTO2);
        pickupExchangeDTO1.setPickupExchangeId(null);
        assertThat(pickupExchangeDTO1).isNotEqualTo(pickupExchangeDTO2);
    }
}
