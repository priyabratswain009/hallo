package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WipQueueOwnerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WipQueueOwnerDTO.class);
        WipQueueOwnerDTO wipQueueOwnerDTO1 = new WipQueueOwnerDTO();
        wipQueueOwnerDTO1.setWipQueueOwnerId(1L);
        WipQueueOwnerDTO wipQueueOwnerDTO2 = new WipQueueOwnerDTO();
        assertThat(wipQueueOwnerDTO1).isNotEqualTo(wipQueueOwnerDTO2);
        wipQueueOwnerDTO2.setWipQueueOwnerId(wipQueueOwnerDTO1.getWipQueueOwnerId());
        assertThat(wipQueueOwnerDTO1).isEqualTo(wipQueueOwnerDTO2);
        wipQueueOwnerDTO2.setWipQueueOwnerId(2L);
        assertThat(wipQueueOwnerDTO1).isNotEqualTo(wipQueueOwnerDTO2);
        wipQueueOwnerDTO1.setWipQueueOwnerId(null);
        assertThat(wipQueueOwnerDTO1).isNotEqualTo(wipQueueOwnerDTO2);
    }
}
