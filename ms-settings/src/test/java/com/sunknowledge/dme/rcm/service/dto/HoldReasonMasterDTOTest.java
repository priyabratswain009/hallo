package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HoldReasonMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoldReasonMasterDTO.class);
        HoldReasonMasterDTO holdReasonMasterDTO1 = new HoldReasonMasterDTO();
        holdReasonMasterDTO1.setHoldReasonId(1L);
        HoldReasonMasterDTO holdReasonMasterDTO2 = new HoldReasonMasterDTO();
        assertThat(holdReasonMasterDTO1).isNotEqualTo(holdReasonMasterDTO2);
        holdReasonMasterDTO2.setHoldReasonId(holdReasonMasterDTO1.getHoldReasonId());
        assertThat(holdReasonMasterDTO1).isEqualTo(holdReasonMasterDTO2);
        holdReasonMasterDTO2.setHoldReasonId(2L);
        assertThat(holdReasonMasterDTO1).isNotEqualTo(holdReasonMasterDTO2);
        holdReasonMasterDTO1.setHoldReasonId(null);
        assertThat(holdReasonMasterDTO1).isNotEqualTo(holdReasonMasterDTO2);
    }
}
