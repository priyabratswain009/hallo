package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceDocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceDocument.class);
        InsuranceDocument insuranceDocument1 = new InsuranceDocument();
        insuranceDocument1.setInsuranceDocumentId(1L);
        InsuranceDocument insuranceDocument2 = new InsuranceDocument();
        insuranceDocument2.setInsuranceDocumentId(insuranceDocument1.getInsuranceDocumentId());
        assertThat(insuranceDocument1).isEqualTo(insuranceDocument2);
        insuranceDocument2.setInsuranceDocumentId(2L);
        assertThat(insuranceDocument1).isNotEqualTo(insuranceDocument2);
        insuranceDocument1.setInsuranceDocumentId(null);
        assertThat(insuranceDocument1).isNotEqualTo(insuranceDocument2);
    }
}
