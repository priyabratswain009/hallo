package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReceiptMasterDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceiptMasterDetailsDTO.class);
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO1 = new ReceiptMasterDetailsDTO();
        receiptMasterDetailsDTO1.setReceiptId(1L);
        ReceiptMasterDetailsDTO receiptMasterDetailsDTO2 = new ReceiptMasterDetailsDTO();
        assertThat(receiptMasterDetailsDTO1).isNotEqualTo(receiptMasterDetailsDTO2);
        receiptMasterDetailsDTO2.setReceiptId(receiptMasterDetailsDTO1.getReceiptId());
        assertThat(receiptMasterDetailsDTO1).isEqualTo(receiptMasterDetailsDTO2);
        receiptMasterDetailsDTO2.setReceiptId(2L);
        assertThat(receiptMasterDetailsDTO1).isNotEqualTo(receiptMasterDetailsDTO2);
        receiptMasterDetailsDTO1.setReceiptId(null);
        assertThat(receiptMasterDetailsDTO1).isNotEqualTo(receiptMasterDetailsDTO2);
    }
}
