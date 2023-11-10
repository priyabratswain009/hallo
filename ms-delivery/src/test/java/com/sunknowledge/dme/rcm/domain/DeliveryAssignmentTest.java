package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryAssignmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryAssignment.class);
        DeliveryAssignment deliveryAssignment1 = new DeliveryAssignment();
        deliveryAssignment1.setDeliveryAssignmentId(1L);
        DeliveryAssignment deliveryAssignment2 = new DeliveryAssignment();
        deliveryAssignment2.setDeliveryAssignmentId(deliveryAssignment1.getDeliveryAssignmentId());
        assertThat(deliveryAssignment1).isEqualTo(deliveryAssignment2);
        deliveryAssignment2.setDeliveryAssignmentId(2L);
        assertThat(deliveryAssignment1).isNotEqualTo(deliveryAssignment2);
        deliveryAssignment1.setDeliveryAssignmentId(null);
        assertThat(deliveryAssignment1).isNotEqualTo(deliveryAssignment2);
    }
}
