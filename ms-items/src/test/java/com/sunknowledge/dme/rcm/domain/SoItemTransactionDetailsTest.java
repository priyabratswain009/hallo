package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoItemTransactionDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoItemTransactionDetails.class);
        SoItemTransactionDetails soItemTransactionDetails1 = new SoItemTransactionDetails();
        soItemTransactionDetails1.setSoItemTransactionDetailsId(1L);
        SoItemTransactionDetails soItemTransactionDetails2 = new SoItemTransactionDetails();
        soItemTransactionDetails2.setSoItemTransactionDetailsId(soItemTransactionDetails1.getSoItemTransactionDetailsId());
        assertThat(soItemTransactionDetails1).isEqualTo(soItemTransactionDetails2);
        soItemTransactionDetails2.setSoItemTransactionDetailsId(2L);
        assertThat(soItemTransactionDetails1).isNotEqualTo(soItemTransactionDetails2);
        soItemTransactionDetails1.setSoItemTransactionDetailsId(null);
        assertThat(soItemTransactionDetails1).isNotEqualTo(soItemTransactionDetails2);
    }
}
