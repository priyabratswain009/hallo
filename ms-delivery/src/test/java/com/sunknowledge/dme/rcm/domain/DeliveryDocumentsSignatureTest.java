package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryDocumentsSignatureTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryDocumentsSignature.class);
        DeliveryDocumentsSignature deliveryDocumentsSignature1 = new DeliveryDocumentsSignature();
        deliveryDocumentsSignature1.setDeliveryDocumentSignatureId(1L);
        DeliveryDocumentsSignature deliveryDocumentsSignature2 = new DeliveryDocumentsSignature();
        deliveryDocumentsSignature2.setDeliveryDocumentSignatureId(deliveryDocumentsSignature1.getDeliveryDocumentSignatureId());
        assertThat(deliveryDocumentsSignature1).isEqualTo(deliveryDocumentsSignature2);
        deliveryDocumentsSignature2.setDeliveryDocumentSignatureId(2L);
        assertThat(deliveryDocumentsSignature1).isNotEqualTo(deliveryDocumentsSignature2);
        deliveryDocumentsSignature1.setDeliveryDocumentSignatureId(null);
        assertThat(deliveryDocumentsSignature1).isNotEqualTo(deliveryDocumentsSignature2);
    }
}
