package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoLcdDocRefTransactionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoLcdDocRefTransactionDTO.class);
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO1 = new SoLcdDocRefTransactionDTO();
        soLcdDocRefTransactionDTO1.setSoLcdDocRefId(1L);
        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO2 = new SoLcdDocRefTransactionDTO();
        assertThat(soLcdDocRefTransactionDTO1).isNotEqualTo(soLcdDocRefTransactionDTO2);
        soLcdDocRefTransactionDTO2.setSoLcdDocRefId(soLcdDocRefTransactionDTO1.getSoLcdDocRefId());
        assertThat(soLcdDocRefTransactionDTO1).isEqualTo(soLcdDocRefTransactionDTO2);
        soLcdDocRefTransactionDTO2.setSoLcdDocRefId(2L);
        assertThat(soLcdDocRefTransactionDTO1).isNotEqualTo(soLcdDocRefTransactionDTO2);
        soLcdDocRefTransactionDTO1.setSoLcdDocRefId(null);
        assertThat(soLcdDocRefTransactionDTO1).isNotEqualTo(soLcdDocRefTransactionDTO2);
    }
}
