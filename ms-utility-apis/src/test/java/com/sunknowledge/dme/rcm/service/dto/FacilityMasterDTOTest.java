package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FacilityMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FacilityMasterDTO.class);
        FacilityMasterDTO facilityMasterDTO1 = new FacilityMasterDTO();
        facilityMasterDTO1.setFacilityId(1L);
        FacilityMasterDTO facilityMasterDTO2 = new FacilityMasterDTO();
        assertThat(facilityMasterDTO1).isNotEqualTo(facilityMasterDTO2);
        facilityMasterDTO2.setFacilityId(facilityMasterDTO1.getFacilityId());
        assertThat(facilityMasterDTO1).isEqualTo(facilityMasterDTO2);
        facilityMasterDTO2.setFacilityId(2L);
        assertThat(facilityMasterDTO1).isNotEqualTo(facilityMasterDTO2);
        facilityMasterDTO1.setFacilityId(null);
        assertThat(facilityMasterDTO1).isNotEqualTo(facilityMasterDTO2);
    }
}
