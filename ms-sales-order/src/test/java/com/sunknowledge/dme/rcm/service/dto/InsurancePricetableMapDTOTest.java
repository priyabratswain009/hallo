package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsurancePricetableMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsurancePricetableMapDTO.class);
        InsurancePricetableMapDTO insurancePricetableMapDTO1 = new InsurancePricetableMapDTO();
        insurancePricetableMapDTO1.setInsurancePricetableMapId(1L);
        InsurancePricetableMapDTO insurancePricetableMapDTO2 = new InsurancePricetableMapDTO();
        assertThat(insurancePricetableMapDTO1).isNotEqualTo(insurancePricetableMapDTO2);
        insurancePricetableMapDTO2.setInsurancePricetableMapId(insurancePricetableMapDTO1.getInsurancePricetableMapId());
        assertThat(insurancePricetableMapDTO1).isEqualTo(insurancePricetableMapDTO2);
        insurancePricetableMapDTO2.setInsurancePricetableMapId(2L);
        assertThat(insurancePricetableMapDTO1).isNotEqualTo(insurancePricetableMapDTO2);
        insurancePricetableMapDTO1.setInsurancePricetableMapId(null);
        assertThat(insurancePricetableMapDTO1).isNotEqualTo(insurancePricetableMapDTO2);
    }
}
