package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceMaster.class);
        InsuranceMaster insuranceMaster1 = new InsuranceMaster();
        insuranceMaster1.setInsuranceId(1L);
        InsuranceMaster insuranceMaster2 = new InsuranceMaster();
        insuranceMaster2.setInsuranceId(insuranceMaster1.getInsuranceId());
        assertThat(insuranceMaster1).isEqualTo(insuranceMaster2);
        insuranceMaster2.setInsuranceId(2L);
        assertThat(insuranceMaster1).isNotEqualTo(insuranceMaster2);
        insuranceMaster1.setInsuranceId(null);
        assertThat(insuranceMaster1).isNotEqualTo(insuranceMaster2);
    }
}
