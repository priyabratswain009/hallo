package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Transaction835MasterDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transaction835MasterDetails.class);
        Transaction835MasterDetails transaction835MasterDetails1 = new Transaction835MasterDetails();
        transaction835MasterDetails1.setTransactionId(1L);
        Transaction835MasterDetails transaction835MasterDetails2 = new Transaction835MasterDetails();
        transaction835MasterDetails2.setTransactionId(transaction835MasterDetails1.getTransactionId());
        assertThat(transaction835MasterDetails1).isEqualTo(transaction835MasterDetails2);
        transaction835MasterDetails2.setTransactionId(2L);
        assertThat(transaction835MasterDetails1).isNotEqualTo(transaction835MasterDetails2);
        transaction835MasterDetails1.setTransactionId(null);
        assertThat(transaction835MasterDetails1).isNotEqualTo(transaction835MasterDetails2);
    }
}
