package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryDocumentsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryDocuments.class);
        DeliveryDocuments deliveryDocuments1 = new DeliveryDocuments();
        deliveryDocuments1.setDeliveryDocId(1L);
        DeliveryDocuments deliveryDocuments2 = new DeliveryDocuments();
        deliveryDocuments2.setDeliveryDocId(deliveryDocuments1.getDeliveryDocId());
        assertThat(deliveryDocuments1).isEqualTo(deliveryDocuments2);
        deliveryDocuments2.setDeliveryDocId(2L);
        assertThat(deliveryDocuments1).isNotEqualTo(deliveryDocuments2);
        deliveryDocuments1.setDeliveryDocId(null);
        assertThat(deliveryDocuments1).isNotEqualTo(deliveryDocuments2);
    }
}
