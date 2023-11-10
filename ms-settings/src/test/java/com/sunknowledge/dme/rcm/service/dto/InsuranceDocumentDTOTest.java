package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceDocumentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceDocumentDTO.class);
        InsuranceDocumentDTO insuranceDocumentDTO1 = new InsuranceDocumentDTO();
        insuranceDocumentDTO1.setInsuranceDocumentId(1L);
        InsuranceDocumentDTO insuranceDocumentDTO2 = new InsuranceDocumentDTO();
        assertThat(insuranceDocumentDTO1).isNotEqualTo(insuranceDocumentDTO2);
        insuranceDocumentDTO2.setInsuranceDocumentId(insuranceDocumentDTO1.getInsuranceDocumentId());
        assertThat(insuranceDocumentDTO1).isEqualTo(insuranceDocumentDTO2);
        insuranceDocumentDTO2.setInsuranceDocumentId(2L);
        assertThat(insuranceDocumentDTO1).isNotEqualTo(insuranceDocumentDTO2);
        insuranceDocumentDTO1.setInsuranceDocumentId(null);
        assertThat(insuranceDocumentDTO1).isNotEqualTo(insuranceDocumentDTO2);
    }
}
