package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryTicketDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryTicketDTO.class);
        DeliveryTicketDTO deliveryTicketDTO1 = new DeliveryTicketDTO();
        deliveryTicketDTO1.setDeliveryTicketId(1L);
        DeliveryTicketDTO deliveryTicketDTO2 = new DeliveryTicketDTO();
        assertThat(deliveryTicketDTO1).isNotEqualTo(deliveryTicketDTO2);
        deliveryTicketDTO2.setDeliveryTicketId(deliveryTicketDTO1.getDeliveryTicketId());
        assertThat(deliveryTicketDTO1).isEqualTo(deliveryTicketDTO2);
        deliveryTicketDTO2.setDeliveryTicketId(2L);
        assertThat(deliveryTicketDTO1).isNotEqualTo(deliveryTicketDTO2);
        deliveryTicketDTO1.setDeliveryTicketId(null);
        assertThat(deliveryTicketDTO1).isNotEqualTo(deliveryTicketDTO2);
    }
}
