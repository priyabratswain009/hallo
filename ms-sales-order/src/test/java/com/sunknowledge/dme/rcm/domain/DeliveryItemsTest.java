package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryItemsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryItems.class);
        DeliveryItems deliveryItems1 = new DeliveryItems();
        deliveryItems1.setDeliveryItemId(1L);
        DeliveryItems deliveryItems2 = new DeliveryItems();
        deliveryItems2.setDeliveryItemId(deliveryItems1.getDeliveryItemId());
        assertThat(deliveryItems1).isEqualTo(deliveryItems2);
        deliveryItems2.setDeliveryItemId(2L);
        assertThat(deliveryItems1).isNotEqualTo(deliveryItems2);
        deliveryItems1.setDeliveryItemId(null);
        assertThat(deliveryItems1).isNotEqualTo(deliveryItems2);
    }
}
