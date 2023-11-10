package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PickupExchangeItemsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickupExchangeItems.class);
        PickupExchangeItems pickupExchangeItems1 = new PickupExchangeItems();
        pickupExchangeItems1.setPickupExchangeItemId(1L);
        PickupExchangeItems pickupExchangeItems2 = new PickupExchangeItems();
        pickupExchangeItems2.setPickupExchangeItemId(pickupExchangeItems1.getPickupExchangeItemId());
        assertThat(pickupExchangeItems1).isEqualTo(pickupExchangeItems2);
        pickupExchangeItems2.setPickupExchangeItemId(2L);
        assertThat(pickupExchangeItems1).isNotEqualTo(pickupExchangeItems2);
        pickupExchangeItems1.setPickupExchangeItemId(null);
        assertThat(pickupExchangeItems1).isNotEqualTo(pickupExchangeItems2);
    }
}
