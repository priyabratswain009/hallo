package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AbnDeliveryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbnDeliveryDTO.class);
        AbnDeliveryDTO abnDeliveryDTO1 = new AbnDeliveryDTO();
        abnDeliveryDTO1.setAbnDeliveryId(1L);
        AbnDeliveryDTO abnDeliveryDTO2 = new AbnDeliveryDTO();
        assertThat(abnDeliveryDTO1).isNotEqualTo(abnDeliveryDTO2);
        abnDeliveryDTO2.setAbnDeliveryId(abnDeliveryDTO1.getAbnDeliveryId());
        assertThat(abnDeliveryDTO1).isEqualTo(abnDeliveryDTO2);
        abnDeliveryDTO2.setAbnDeliveryId(2L);
        assertThat(abnDeliveryDTO1).isNotEqualTo(abnDeliveryDTO2);
        abnDeliveryDTO1.setAbnDeliveryId(null);
        assertThat(abnDeliveryDTO1).isNotEqualTo(abnDeliveryDTO2);
    }
}
