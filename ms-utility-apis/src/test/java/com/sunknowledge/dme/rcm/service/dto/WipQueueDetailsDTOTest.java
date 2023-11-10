package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WipQueueDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WipQueueDetailsDTO.class);
        WipQueueDetailsDTO wipQueueDetailsDTO1 = new WipQueueDetailsDTO();
        wipQueueDetailsDTO1.setWipQueueDetailsId(1L);
        WipQueueDetailsDTO wipQueueDetailsDTO2 = new WipQueueDetailsDTO();
        assertThat(wipQueueDetailsDTO1).isNotEqualTo(wipQueueDetailsDTO2);
        wipQueueDetailsDTO2.setWipQueueDetailsId(wipQueueDetailsDTO1.getWipQueueDetailsId());
        assertThat(wipQueueDetailsDTO1).isEqualTo(wipQueueDetailsDTO2);
        wipQueueDetailsDTO2.setWipQueueDetailsId(2L);
        assertThat(wipQueueDetailsDTO1).isNotEqualTo(wipQueueDetailsDTO2);
        wipQueueDetailsDTO1.setWipQueueDetailsId(null);
        assertThat(wipQueueDetailsDTO1).isNotEqualTo(wipQueueDetailsDTO2);
    }
}
