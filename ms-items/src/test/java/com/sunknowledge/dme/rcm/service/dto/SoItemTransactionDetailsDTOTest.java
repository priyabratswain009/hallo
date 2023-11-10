package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoItemTransactionDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoItemTransactionDetailsDTO.class);
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO1 = new SoItemTransactionDetailsDTO();
        soItemTransactionDetailsDTO1.setSoItemTransactionDetailsId(1L);
        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO2 = new SoItemTransactionDetailsDTO();
        assertThat(soItemTransactionDetailsDTO1).isNotEqualTo(soItemTransactionDetailsDTO2);
        soItemTransactionDetailsDTO2.setSoItemTransactionDetailsId(soItemTransactionDetailsDTO1.getSoItemTransactionDetailsId());
        assertThat(soItemTransactionDetailsDTO1).isEqualTo(soItemTransactionDetailsDTO2);
        soItemTransactionDetailsDTO2.setSoItemTransactionDetailsId(2L);
        assertThat(soItemTransactionDetailsDTO1).isNotEqualTo(soItemTransactionDetailsDTO2);
        soItemTransactionDetailsDTO1.setSoItemTransactionDetailsId(null);
        assertThat(soItemTransactionDetailsDTO1).isNotEqualTo(soItemTransactionDetailsDTO2);
    }
}
