package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HcpcsDmeGroupMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HcpcsDmeGroupMasterDTO.class);
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO1 = new HcpcsDmeGroupMasterDTO();
        hcpcsDmeGroupMasterDTO1.setHcpcsDmeId(1L);
        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO2 = new HcpcsDmeGroupMasterDTO();
        assertThat(hcpcsDmeGroupMasterDTO1).isNotEqualTo(hcpcsDmeGroupMasterDTO2);
        hcpcsDmeGroupMasterDTO2.setHcpcsDmeId(hcpcsDmeGroupMasterDTO1.getHcpcsDmeId());
        assertThat(hcpcsDmeGroupMasterDTO1).isEqualTo(hcpcsDmeGroupMasterDTO2);
        hcpcsDmeGroupMasterDTO2.setHcpcsDmeId(2L);
        assertThat(hcpcsDmeGroupMasterDTO1).isNotEqualTo(hcpcsDmeGroupMasterDTO2);
        hcpcsDmeGroupMasterDTO1.setHcpcsDmeId(null);
        assertThat(hcpcsDmeGroupMasterDTO1).isNotEqualTo(hcpcsDmeGroupMasterDTO2);
    }
}
