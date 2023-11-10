package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IcdMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IcdMasterDTO.class);
        IcdMasterDTO icdMasterDTO1 = new IcdMasterDTO();
        icdMasterDTO1.setIcdMasterId(1L);
        IcdMasterDTO icdMasterDTO2 = new IcdMasterDTO();
        assertThat(icdMasterDTO1).isNotEqualTo(icdMasterDTO2);
        icdMasterDTO2.setIcdMasterId(icdMasterDTO1.getIcdMasterId());
        assertThat(icdMasterDTO1).isEqualTo(icdMasterDTO2);
        icdMasterDTO2.setIcdMasterId(2L);
        assertThat(icdMasterDTO1).isNotEqualTo(icdMasterDTO2);
        icdMasterDTO1.setIcdMasterId(null);
        assertThat(icdMasterDTO1).isNotEqualTo(icdMasterDTO2);
    }
}
