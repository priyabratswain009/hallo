package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryItemsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryItemsDTO.class);
        DeliveryItemsDTO deliveryItemsDTO1 = new DeliveryItemsDTO();
        deliveryItemsDTO1.setDeliveryItemId(1L);
        DeliveryItemsDTO deliveryItemsDTO2 = new DeliveryItemsDTO();
        assertThat(deliveryItemsDTO1).isNotEqualTo(deliveryItemsDTO2);
        deliveryItemsDTO2.setDeliveryItemId(deliveryItemsDTO1.getDeliveryItemId());
        assertThat(deliveryItemsDTO1).isEqualTo(deliveryItemsDTO2);
        deliveryItemsDTO2.setDeliveryItemId(2L);
        assertThat(deliveryItemsDTO1).isNotEqualTo(deliveryItemsDTO2);
        deliveryItemsDTO1.setDeliveryItemId(null);
        assertThat(deliveryItemsDTO1).isNotEqualTo(deliveryItemsDTO2);
    }
}
