package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StopReasonMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StopReasonMasterDTO.class);
        StopReasonMasterDTO stopReasonMasterDTO1 = new StopReasonMasterDTO();
        stopReasonMasterDTO1.setStopReasonId(1L);
        StopReasonMasterDTO stopReasonMasterDTO2 = new StopReasonMasterDTO();
        assertThat(stopReasonMasterDTO1).isNotEqualTo(stopReasonMasterDTO2);
        stopReasonMasterDTO2.setStopReasonId(stopReasonMasterDTO1.getStopReasonId());
        assertThat(stopReasonMasterDTO1).isEqualTo(stopReasonMasterDTO2);
        stopReasonMasterDTO2.setStopReasonId(2L);
        assertThat(stopReasonMasterDTO1).isNotEqualTo(stopReasonMasterDTO2);
        stopReasonMasterDTO1.setStopReasonId(null);
        assertThat(stopReasonMasterDTO1).isNotEqualTo(stopReasonMasterDTO2);
    }
}
