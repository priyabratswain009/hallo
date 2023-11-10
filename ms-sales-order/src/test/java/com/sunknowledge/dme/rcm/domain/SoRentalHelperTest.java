package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoRentalHelperTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoRentalHelper.class);
        SoRentalHelper soRentalHelper1 = new SoRentalHelper();
        soRentalHelper1.setSoRentalHelperId(1L);
        SoRentalHelper soRentalHelper2 = new SoRentalHelper();
        soRentalHelper2.setSoRentalHelperId(soRentalHelper1.getSoRentalHelperId());
        assertThat(soRentalHelper1).isEqualTo(soRentalHelper2);
        soRentalHelper2.setSoRentalHelperId(2L);
        assertThat(soRentalHelper1).isNotEqualTo(soRentalHelper2);
        soRentalHelper1.setSoRentalHelperId(null);
        assertThat(soRentalHelper1).isNotEqualTo(soRentalHelper2);
    }
}
