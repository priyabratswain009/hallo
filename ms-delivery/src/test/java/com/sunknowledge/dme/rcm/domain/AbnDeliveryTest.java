package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AbnDeliveryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbnDelivery.class);
        AbnDelivery abnDelivery1 = new AbnDelivery();
        abnDelivery1.setAbnDeliveryId(1L);
        AbnDelivery abnDelivery2 = new AbnDelivery();
        abnDelivery2.setAbnDeliveryId(abnDelivery1.getAbnDeliveryId());
        assertThat(abnDelivery1).isEqualTo(abnDelivery2);
        abnDelivery2.setAbnDeliveryId(2L);
        assertThat(abnDelivery1).isNotEqualTo(abnDelivery2);
        abnDelivery1.setAbnDeliveryId(null);
        assertThat(abnDelivery1).isNotEqualTo(abnDelivery2);
    }
}
