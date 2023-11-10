package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoLcdCoverageCriteriaTransactionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoLcdCoverageCriteriaTransaction.class);
        SoLcdCoverageCriteriaTransaction soLcdCoverageCriteriaTransaction1 = new SoLcdCoverageCriteriaTransaction();
        soLcdCoverageCriteriaTransaction1.setSoLcdDocRefId(1L);
        SoLcdCoverageCriteriaTransaction soLcdCoverageCriteriaTransaction2 = new SoLcdCoverageCriteriaTransaction();
        soLcdCoverageCriteriaTransaction2.setSoLcdDocRefId(soLcdCoverageCriteriaTransaction1.getSoLcdDocRefId());
        assertThat(soLcdCoverageCriteriaTransaction1).isEqualTo(soLcdCoverageCriteriaTransaction2);
        soLcdCoverageCriteriaTransaction2.setSoLcdDocRefId(2L);
        assertThat(soLcdCoverageCriteriaTransaction1).isNotEqualTo(soLcdCoverageCriteriaTransaction2);
        soLcdCoverageCriteriaTransaction1.setSoLcdDocRefId(null);
        assertThat(soLcdCoverageCriteriaTransaction1).isNotEqualTo(soLcdCoverageCriteriaTransaction2);
    }
}
