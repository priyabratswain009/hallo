package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DmeGroupChecklistMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmeGroupChecklistMasterDTO.class);
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO1 = new DmeGroupChecklistMasterDTO();
        dmeGroupChecklistMasterDTO1.setDmeGroupChecklistId(1L);
        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO2 = new DmeGroupChecklistMasterDTO();
        assertThat(dmeGroupChecklistMasterDTO1).isNotEqualTo(dmeGroupChecklistMasterDTO2);
        dmeGroupChecklistMasterDTO2.setDmeGroupChecklistId(dmeGroupChecklistMasterDTO1.getDmeGroupChecklistId());
        assertThat(dmeGroupChecklistMasterDTO1).isEqualTo(dmeGroupChecklistMasterDTO2);
        dmeGroupChecklistMasterDTO2.setDmeGroupChecklistId(2L);
        assertThat(dmeGroupChecklistMasterDTO1).isNotEqualTo(dmeGroupChecklistMasterDTO2);
        dmeGroupChecklistMasterDTO1.setDmeGroupChecklistId(null);
        assertThat(dmeGroupChecklistMasterDTO1).isNotEqualTo(dmeGroupChecklistMasterDTO2);
    }
}
