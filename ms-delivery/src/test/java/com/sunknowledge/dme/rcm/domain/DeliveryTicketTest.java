package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryTicketTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryTicket.class);
        DeliveryTicket deliveryTicket1 = new DeliveryTicket();
        deliveryTicket1.setDeliveryTicketId(1L);
        DeliveryTicket deliveryTicket2 = new DeliveryTicket();
        deliveryTicket2.setDeliveryTicketId(deliveryTicket1.getDeliveryTicketId());
        assertThat(deliveryTicket1).isEqualTo(deliveryTicket2);
        deliveryTicket2.setDeliveryTicketId(2L);
        assertThat(deliveryTicket1).isNotEqualTo(deliveryTicket2);
        deliveryTicket1.setDeliveryTicketId(null);
        assertThat(deliveryTicket1).isNotEqualTo(deliveryTicket2);
    }
}
