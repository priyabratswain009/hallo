package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryDocumentsSignatureDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryDocumentsSignatureDTO.class);
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO1 = new DeliveryDocumentsSignatureDTO();
        deliveryDocumentsSignatureDTO1.setDeliveryDocumentSignatureId(1L);
        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO2 = new DeliveryDocumentsSignatureDTO();
        assertThat(deliveryDocumentsSignatureDTO1).isNotEqualTo(deliveryDocumentsSignatureDTO2);
        deliveryDocumentsSignatureDTO2.setDeliveryDocumentSignatureId(deliveryDocumentsSignatureDTO1.getDeliveryDocumentSignatureId());
        assertThat(deliveryDocumentsSignatureDTO1).isEqualTo(deliveryDocumentsSignatureDTO2);
        deliveryDocumentsSignatureDTO2.setDeliveryDocumentSignatureId(2L);
        assertThat(deliveryDocumentsSignatureDTO1).isNotEqualTo(deliveryDocumentsSignatureDTO2);
        deliveryDocumentsSignatureDTO1.setDeliveryDocumentSignatureId(null);
        assertThat(deliveryDocumentsSignatureDTO1).isNotEqualTo(deliveryDocumentsSignatureDTO2);
    }
}
