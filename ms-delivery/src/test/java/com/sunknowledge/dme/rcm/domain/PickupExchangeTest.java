package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PickupExchangeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickupExchange.class);
        PickupExchange pickupExchange1 = new PickupExchange();
        pickupExchange1.setPickupExchangeId(1L);
        PickupExchange pickupExchange2 = new PickupExchange();
        pickupExchange2.setPickupExchangeId(pickupExchange1.getPickupExchangeId());
        assertThat(pickupExchange1).isEqualTo(pickupExchange2);
        pickupExchange2.setPickupExchangeId(2L);
        assertThat(pickupExchange1).isNotEqualTo(pickupExchange2);
        pickupExchange1.setPickupExchangeId(null);
        assertThat(pickupExchange1).isNotEqualTo(pickupExchange2);
    }
}
