package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryAbnDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryAbnData.class);
        DeliveryAbnData deliveryAbnData1 = new DeliveryAbnData();
        deliveryAbnData1.setDeliveryAbnDataId(1L);
        DeliveryAbnData deliveryAbnData2 = new DeliveryAbnData();
        deliveryAbnData2.setDeliveryAbnDataId(deliveryAbnData1.getDeliveryAbnDataId());
        assertThat(deliveryAbnData1).isEqualTo(deliveryAbnData2);
        deliveryAbnData2.setDeliveryAbnDataId(2L);
        assertThat(deliveryAbnData1).isNotEqualTo(deliveryAbnData2);
        deliveryAbnData1.setDeliveryAbnDataId(null);
        assertThat(deliveryAbnData1).isNotEqualTo(deliveryAbnData2);
    }
}
