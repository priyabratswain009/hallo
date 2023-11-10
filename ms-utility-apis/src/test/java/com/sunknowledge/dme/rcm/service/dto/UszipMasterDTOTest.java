package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UszipMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UszipMasterDTO.class);
        UszipMasterDTO uszipMasterDTO1 = new UszipMasterDTO();
        uszipMasterDTO1.setUszipMasterId(1L);
        UszipMasterDTO uszipMasterDTO2 = new UszipMasterDTO();
        assertThat(uszipMasterDTO1).isNotEqualTo(uszipMasterDTO2);
        uszipMasterDTO2.setUszipMasterId(uszipMasterDTO1.getUszipMasterId());
        assertThat(uszipMasterDTO1).isEqualTo(uszipMasterDTO2);
        uszipMasterDTO2.setUszipMasterId(2L);
        assertThat(uszipMasterDTO1).isNotEqualTo(uszipMasterDTO2);
        uszipMasterDTO1.setUszipMasterId(null);
        assertThat(uszipMasterDTO1).isNotEqualTo(uszipMasterDTO2);
    }
}
