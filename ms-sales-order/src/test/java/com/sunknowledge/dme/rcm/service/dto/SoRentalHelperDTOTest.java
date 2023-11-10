package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoRentalHelperDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoRentalHelperDTO.class);
        SoRentalHelperDTO soRentalHelperDTO1 = new SoRentalHelperDTO();
        soRentalHelperDTO1.setSoRentalHelperId(1L);
        SoRentalHelperDTO soRentalHelperDTO2 = new SoRentalHelperDTO();
        assertThat(soRentalHelperDTO1).isNotEqualTo(soRentalHelperDTO2);
        soRentalHelperDTO2.setSoRentalHelperId(soRentalHelperDTO1.getSoRentalHelperId());
        assertThat(soRentalHelperDTO1).isEqualTo(soRentalHelperDTO2);
        soRentalHelperDTO2.setSoRentalHelperId(2L);
        assertThat(soRentalHelperDTO1).isNotEqualTo(soRentalHelperDTO2);
        soRentalHelperDTO1.setSoRentalHelperId(null);
        assertThat(soRentalHelperDTO1).isNotEqualTo(soRentalHelperDTO2);
    }
}
