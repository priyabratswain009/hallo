package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryPatientCommunicationsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryPatientCommunications.class);
        DeliveryPatientCommunications deliveryPatientCommunications1 = new DeliveryPatientCommunications();
        deliveryPatientCommunications1.setDeliveryPatientCommunicationsId(1L);
        DeliveryPatientCommunications deliveryPatientCommunications2 = new DeliveryPatientCommunications();
        deliveryPatientCommunications2.setDeliveryPatientCommunicationsId(
            deliveryPatientCommunications1.getDeliveryPatientCommunicationsId()
        );
        assertThat(deliveryPatientCommunications1).isEqualTo(deliveryPatientCommunications2);
        deliveryPatientCommunications2.setDeliveryPatientCommunicationsId(2L);
        assertThat(deliveryPatientCommunications1).isNotEqualTo(deliveryPatientCommunications2);
        deliveryPatientCommunications1.setDeliveryPatientCommunicationsId(null);
        assertThat(deliveryPatientCommunications1).isNotEqualTo(deliveryPatientCommunications2);
    }
}
