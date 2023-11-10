package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoLcdCoverageCriteriaTransactionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoLcdCoverageCriteriaTransactionDTO.class);
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO1 = new SoLcdCoverageCriteriaTransactionDTO();
        soLcdCoverageCriteriaTransactionDTO1.setSoLcdDocRefId(1L);
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO2 = new SoLcdCoverageCriteriaTransactionDTO();
        assertThat(soLcdCoverageCriteriaTransactionDTO1).isNotEqualTo(soLcdCoverageCriteriaTransactionDTO2);
        soLcdCoverageCriteriaTransactionDTO2.setSoLcdDocRefId(soLcdCoverageCriteriaTransactionDTO1.getSoLcdDocRefId());
        assertThat(soLcdCoverageCriteriaTransactionDTO1).isEqualTo(soLcdCoverageCriteriaTransactionDTO2);
        soLcdCoverageCriteriaTransactionDTO2.setSoLcdDocRefId(2L);
        assertThat(soLcdCoverageCriteriaTransactionDTO1).isNotEqualTo(soLcdCoverageCriteriaTransactionDTO2);
        soLcdCoverageCriteriaTransactionDTO1.setSoLcdDocRefId(null);
        assertThat(soLcdCoverageCriteriaTransactionDTO1).isNotEqualTo(soLcdCoverageCriteriaTransactionDTO2);
    }
}
