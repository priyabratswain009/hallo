package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoLcdDocRefTransactionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoLcdDocRefTransaction.class);
        SoLcdDocRefTransaction soLcdDocRefTransaction1 = new SoLcdDocRefTransaction();
        soLcdDocRefTransaction1.setSoLcdDocRefId(1L);
        SoLcdDocRefTransaction soLcdDocRefTransaction2 = new SoLcdDocRefTransaction();
        soLcdDocRefTransaction2.setSoLcdDocRefId(soLcdDocRefTransaction1.getSoLcdDocRefId());
        assertThat(soLcdDocRefTransaction1).isEqualTo(soLcdDocRefTransaction2);
        soLcdDocRefTransaction2.setSoLcdDocRefId(2L);
        assertThat(soLcdDocRefTransaction1).isNotEqualTo(soLcdDocRefTransaction2);
        soLcdDocRefTransaction1.setSoLcdDocRefId(null);
        assertThat(soLcdDocRefTransaction1).isNotEqualTo(soLcdDocRefTransaction2);
    }
}
