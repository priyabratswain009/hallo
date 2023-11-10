package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PayerMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayerMasterDTO.class);
        PayerMasterDTO payerMasterDTO1 = new PayerMasterDTO();
        payerMasterDTO1.setPayerMasterId(1L);
        PayerMasterDTO payerMasterDTO2 = new PayerMasterDTO();
        assertThat(payerMasterDTO1).isNotEqualTo(payerMasterDTO2);
        payerMasterDTO2.setPayerMasterId(payerMasterDTO1.getPayerMasterId());
        assertThat(payerMasterDTO1).isEqualTo(payerMasterDTO2);
        payerMasterDTO2.setPayerMasterId(2L);
        assertThat(payerMasterDTO1).isNotEqualTo(payerMasterDTO2);
        payerMasterDTO1.setPayerMasterId(null);
        assertThat(payerMasterDTO1).isNotEqualTo(payerMasterDTO2);
    }
}
