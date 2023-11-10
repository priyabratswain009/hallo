package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HoldReasonMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoldReasonMaster.class);
        HoldReasonMaster holdReasonMaster1 = new HoldReasonMaster();
        holdReasonMaster1.setHoldReasonId(1L);
        HoldReasonMaster holdReasonMaster2 = new HoldReasonMaster();
        holdReasonMaster2.setHoldReasonId(holdReasonMaster1.getHoldReasonId());
        assertThat(holdReasonMaster1).isEqualTo(holdReasonMaster2);
        holdReasonMaster2.setHoldReasonId(2L);
        assertThat(holdReasonMaster1).isNotEqualTo(holdReasonMaster2);
        holdReasonMaster1.setHoldReasonId(null);
        assertThat(holdReasonMaster1).isNotEqualTo(holdReasonMaster2);
    }
}
