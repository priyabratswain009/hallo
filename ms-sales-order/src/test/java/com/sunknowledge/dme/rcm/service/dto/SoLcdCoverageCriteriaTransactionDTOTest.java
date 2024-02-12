package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoLcdCoverageCriteriaTransactionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoLcdCoverageCriteriaTransactionDTO.class);
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO1 = new SoLcdCoverageCriteriaTransactionDTO();
        soLcdCoverageCriteriaTransactionDTO1.setSoLcdCoverageCriteriaTransactionId(1L);
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO2 = new SoLcdCoverageCriteriaTransactionDTO();
        assertThat(soLcdCoverageCriteriaTransactionDTO1).isNotEqualTo(soLcdCoverageCriteriaTransactionDTO2);
        soLcdCoverageCriteriaTransactionDTO2.setSoLcdCoverageCriteriaTransactionId(
            soLcdCoverageCriteriaTransactionDTO1.getSoLcdCoverageCriteriaTransactionId()
        );
        assertThat(soLcdCoverageCriteriaTransactionDTO1).isEqualTo(soLcdCoverageCriteriaTransactionDTO2);
        soLcdCoverageCriteriaTransactionDTO2.setSoLcdCoverageCriteriaTransactionId(2L);
        assertThat(soLcdCoverageCriteriaTransactionDTO1).isNotEqualTo(soLcdCoverageCriteriaTransactionDTO2);
        soLcdCoverageCriteriaTransactionDTO1.setSoLcdCoverageCriteriaTransactionId(null);
        assertThat(soLcdCoverageCriteriaTransactionDTO1).isNotEqualTo(soLcdCoverageCriteriaTransactionDTO2);
    }
}
