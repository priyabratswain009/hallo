package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PayerMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayerMaster.class);
        PayerMaster payerMaster1 = new PayerMaster();
        payerMaster1.setPayerMasterId(1L);
        PayerMaster payerMaster2 = new PayerMaster();
        payerMaster2.setPayerMasterId(payerMaster1.getPayerMasterId());
        assertThat(payerMaster1).isEqualTo(payerMaster2);
        payerMaster2.setPayerMasterId(2L);
        assertThat(payerMaster1).isNotEqualTo(payerMaster2);
        payerMaster1.setPayerMasterId(null);
        assertThat(payerMaster1).isNotEqualTo(payerMaster2);
    }
}
