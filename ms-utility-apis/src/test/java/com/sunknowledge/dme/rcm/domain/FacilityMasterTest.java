package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FacilityMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FacilityMaster.class);
        FacilityMaster facilityMaster1 = new FacilityMaster();
        facilityMaster1.setFacilityId(1L);
        FacilityMaster facilityMaster2 = new FacilityMaster();
        facilityMaster2.setFacilityId(facilityMaster1.getFacilityId());
        assertThat(facilityMaster1).isEqualTo(facilityMaster2);
        facilityMaster2.setFacilityId(2L);
        assertThat(facilityMaster1).isNotEqualTo(facilityMaster2);
        facilityMaster1.setFacilityId(null);
        assertThat(facilityMaster1).isNotEqualTo(facilityMaster2);
    }
}
