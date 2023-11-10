package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceGroupMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceGroupMaster.class);
        InsuranceGroupMaster insuranceGroupMaster1 = new InsuranceGroupMaster();
        insuranceGroupMaster1.setInsuranceGroupMasterId(1L);
        InsuranceGroupMaster insuranceGroupMaster2 = new InsuranceGroupMaster();
        insuranceGroupMaster2.setInsuranceGroupMasterId(insuranceGroupMaster1.getInsuranceGroupMasterId());
        assertThat(insuranceGroupMaster1).isEqualTo(insuranceGroupMaster2);
        insuranceGroupMaster2.setInsuranceGroupMasterId(2L);
        assertThat(insuranceGroupMaster1).isNotEqualTo(insuranceGroupMaster2);
        insuranceGroupMaster1.setInsuranceGroupMasterId(null);
        assertThat(insuranceGroupMaster1).isNotEqualTo(insuranceGroupMaster2);
    }
}
