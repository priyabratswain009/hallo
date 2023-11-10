package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryAbnDataDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryAbnDataDTO.class);
        DeliveryAbnDataDTO deliveryAbnDataDTO1 = new DeliveryAbnDataDTO();
        deliveryAbnDataDTO1.setDeliveryAbnDataId(1L);
        DeliveryAbnDataDTO deliveryAbnDataDTO2 = new DeliveryAbnDataDTO();
        assertThat(deliveryAbnDataDTO1).isNotEqualTo(deliveryAbnDataDTO2);
        deliveryAbnDataDTO2.setDeliveryAbnDataId(deliveryAbnDataDTO1.getDeliveryAbnDataId());
        assertThat(deliveryAbnDataDTO1).isEqualTo(deliveryAbnDataDTO2);
        deliveryAbnDataDTO2.setDeliveryAbnDataId(2L);
        assertThat(deliveryAbnDataDTO1).isNotEqualTo(deliveryAbnDataDTO2);
        deliveryAbnDataDTO1.setDeliveryAbnDataId(null);
        assertThat(deliveryAbnDataDTO1).isNotEqualTo(deliveryAbnDataDTO2);
    }
}
