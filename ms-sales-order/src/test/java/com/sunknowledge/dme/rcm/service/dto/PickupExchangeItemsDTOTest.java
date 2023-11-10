package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PickupExchangeItemsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickupExchangeItemsDTO.class);
        PickupExchangeItemsDTO pickupExchangeItemsDTO1 = new PickupExchangeItemsDTO();
        pickupExchangeItemsDTO1.setPickupExchangeItemId(1L);
        PickupExchangeItemsDTO pickupExchangeItemsDTO2 = new PickupExchangeItemsDTO();
        assertThat(pickupExchangeItemsDTO1).isNotEqualTo(pickupExchangeItemsDTO2);
        pickupExchangeItemsDTO2.setPickupExchangeItemId(pickupExchangeItemsDTO1.getPickupExchangeItemId());
        assertThat(pickupExchangeItemsDTO1).isEqualTo(pickupExchangeItemsDTO2);
        pickupExchangeItemsDTO2.setPickupExchangeItemId(2L);
        assertThat(pickupExchangeItemsDTO1).isNotEqualTo(pickupExchangeItemsDTO2);
        pickupExchangeItemsDTO1.setPickupExchangeItemId(null);
        assertThat(pickupExchangeItemsDTO1).isNotEqualTo(pickupExchangeItemsDTO2);
    }
}
