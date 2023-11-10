package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReceiptMasterDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceiptMasterDetails.class);
        ReceiptMasterDetails receiptMasterDetails1 = new ReceiptMasterDetails();
        receiptMasterDetails1.setReceiptId(1L);
        ReceiptMasterDetails receiptMasterDetails2 = new ReceiptMasterDetails();
        receiptMasterDetails2.setReceiptId(receiptMasterDetails1.getReceiptId());
        assertThat(receiptMasterDetails1).isEqualTo(receiptMasterDetails2);
        receiptMasterDetails2.setReceiptId(2L);
        assertThat(receiptMasterDetails1).isNotEqualTo(receiptMasterDetails2);
        receiptMasterDetails1.setReceiptId(null);
        assertThat(receiptMasterDetails1).isNotEqualTo(receiptMasterDetails2);
    }
}
