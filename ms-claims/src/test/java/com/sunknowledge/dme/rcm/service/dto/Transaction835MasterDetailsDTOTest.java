package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Transaction835MasterDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transaction835MasterDetailsDTO.class);
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO1 = new Transaction835MasterDetailsDTO();
        transaction835MasterDetailsDTO1.setTransactionId(1L);
        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO2 = new Transaction835MasterDetailsDTO();
        assertThat(transaction835MasterDetailsDTO1).isNotEqualTo(transaction835MasterDetailsDTO2);
        transaction835MasterDetailsDTO2.setTransactionId(transaction835MasterDetailsDTO1.getTransactionId());
        assertThat(transaction835MasterDetailsDTO1).isEqualTo(transaction835MasterDetailsDTO2);
        transaction835MasterDetailsDTO2.setTransactionId(2L);
        assertThat(transaction835MasterDetailsDTO1).isNotEqualTo(transaction835MasterDetailsDTO2);
        transaction835MasterDetailsDTO1.setTransactionId(null);
        assertThat(transaction835MasterDetailsDTO1).isNotEqualTo(transaction835MasterDetailsDTO2);
    }
}
