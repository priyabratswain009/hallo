package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StopReasonMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StopReasonMaster.class);
        StopReasonMaster stopReasonMaster1 = new StopReasonMaster();
        stopReasonMaster1.setStopReasonId(1L);
        StopReasonMaster stopReasonMaster2 = new StopReasonMaster();
        stopReasonMaster2.setStopReasonId(stopReasonMaster1.getStopReasonId());
        assertThat(stopReasonMaster1).isEqualTo(stopReasonMaster2);
        stopReasonMaster2.setStopReasonId(2L);
        assertThat(stopReasonMaster1).isNotEqualTo(stopReasonMaster2);
        stopReasonMaster1.setStopReasonId(null);
        assertThat(stopReasonMaster1).isNotEqualTo(stopReasonMaster2);
    }
}
