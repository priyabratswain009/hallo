package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryPatientCommunicationsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryPatientCommunicationsDTO.class);
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO1 = new DeliveryPatientCommunicationsDTO();
        deliveryPatientCommunicationsDTO1.setDeliveryPatientCommunicationsId(1L);
        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO2 = new DeliveryPatientCommunicationsDTO();
        assertThat(deliveryPatientCommunicationsDTO1).isNotEqualTo(deliveryPatientCommunicationsDTO2);
        deliveryPatientCommunicationsDTO2.setDeliveryPatientCommunicationsId(
            deliveryPatientCommunicationsDTO1.getDeliveryPatientCommunicationsId()
        );
        assertThat(deliveryPatientCommunicationsDTO1).isEqualTo(deliveryPatientCommunicationsDTO2);
        deliveryPatientCommunicationsDTO2.setDeliveryPatientCommunicationsId(2L);
        assertThat(deliveryPatientCommunicationsDTO1).isNotEqualTo(deliveryPatientCommunicationsDTO2);
        deliveryPatientCommunicationsDTO1.setDeliveryPatientCommunicationsId(null);
        assertThat(deliveryPatientCommunicationsDTO1).isNotEqualTo(deliveryPatientCommunicationsDTO2);
    }
}
