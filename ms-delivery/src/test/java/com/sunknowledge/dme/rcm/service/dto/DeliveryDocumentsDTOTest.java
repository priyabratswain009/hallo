package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryDocumentsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryDocumentsDTO.class);
        DeliveryDocumentsDTO deliveryDocumentsDTO1 = new DeliveryDocumentsDTO();
        deliveryDocumentsDTO1.setDeliveryDocId(1L);
        DeliveryDocumentsDTO deliveryDocumentsDTO2 = new DeliveryDocumentsDTO();
        assertThat(deliveryDocumentsDTO1).isNotEqualTo(deliveryDocumentsDTO2);
        deliveryDocumentsDTO2.setDeliveryDocId(deliveryDocumentsDTO1.getDeliveryDocId());
        assertThat(deliveryDocumentsDTO1).isEqualTo(deliveryDocumentsDTO2);
        deliveryDocumentsDTO2.setDeliveryDocId(2L);
        assertThat(deliveryDocumentsDTO1).isNotEqualTo(deliveryDocumentsDTO2);
        deliveryDocumentsDTO1.setDeliveryDocId(null);
        assertThat(deliveryDocumentsDTO1).isNotEqualTo(deliveryDocumentsDTO2);
    }
}
