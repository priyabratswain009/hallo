package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WipQueueOwnerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WipQueueOwner.class);
        WipQueueOwner wipQueueOwner1 = new WipQueueOwner();
        wipQueueOwner1.setWipQueueOwnerId(1L);
        WipQueueOwner wipQueueOwner2 = new WipQueueOwner();
        wipQueueOwner2.setWipQueueOwnerId(wipQueueOwner1.getWipQueueOwnerId());
        assertThat(wipQueueOwner1).isEqualTo(wipQueueOwner2);
        wipQueueOwner2.setWipQueueOwnerId(2L);
        assertThat(wipQueueOwner1).isNotEqualTo(wipQueueOwner2);
        wipQueueOwner1.setWipQueueOwnerId(null);
        assertThat(wipQueueOwner1).isNotEqualTo(wipQueueOwner2);
    }
}
