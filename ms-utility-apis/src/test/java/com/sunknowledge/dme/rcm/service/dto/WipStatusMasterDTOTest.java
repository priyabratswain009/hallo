package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WipStatusMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WipStatusMasterDTO.class);
        WipStatusMasterDTO wipStatusMasterDTO1 = new WipStatusMasterDTO();
        wipStatusMasterDTO1.setWipStatusId(1L);
        WipStatusMasterDTO wipStatusMasterDTO2 = new WipStatusMasterDTO();
        assertThat(wipStatusMasterDTO1).isNotEqualTo(wipStatusMasterDTO2);
        wipStatusMasterDTO2.setWipStatusId(wipStatusMasterDTO1.getWipStatusId());
        assertThat(wipStatusMasterDTO1).isEqualTo(wipStatusMasterDTO2);
        wipStatusMasterDTO2.setWipStatusId(2L);
        assertThat(wipStatusMasterDTO1).isNotEqualTo(wipStatusMasterDTO2);
        wipStatusMasterDTO1.setWipStatusId(null);
        assertThat(wipStatusMasterDTO1).isNotEqualTo(wipStatusMasterDTO2);
    }
}
