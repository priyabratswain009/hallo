package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryAssignmentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryAssignmentDTO.class);
        DeliveryAssignmentDTO deliveryAssignmentDTO1 = new DeliveryAssignmentDTO();
        deliveryAssignmentDTO1.setDeliveryAssignmentId(1L);
        DeliveryAssignmentDTO deliveryAssignmentDTO2 = new DeliveryAssignmentDTO();
        assertThat(deliveryAssignmentDTO1).isNotEqualTo(deliveryAssignmentDTO2);
        deliveryAssignmentDTO2.setDeliveryAssignmentId(deliveryAssignmentDTO1.getDeliveryAssignmentId());
        assertThat(deliveryAssignmentDTO1).isEqualTo(deliveryAssignmentDTO2);
        deliveryAssignmentDTO2.setDeliveryAssignmentId(2L);
        assertThat(deliveryAssignmentDTO1).isNotEqualTo(deliveryAssignmentDTO2);
        deliveryAssignmentDTO1.setDeliveryAssignmentId(null);
        assertThat(deliveryAssignmentDTO1).isNotEqualTo(deliveryAssignmentDTO2);
    }
}
