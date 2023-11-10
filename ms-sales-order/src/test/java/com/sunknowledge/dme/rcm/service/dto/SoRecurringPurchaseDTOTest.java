package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoRecurringPurchaseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoRecurringPurchaseDTO.class);
        SoRecurringPurchaseDTO soRecurringPurchaseDTO1 = new SoRecurringPurchaseDTO();
        soRecurringPurchaseDTO1.setRpId(1L);
        SoRecurringPurchaseDTO soRecurringPurchaseDTO2 = new SoRecurringPurchaseDTO();
        assertThat(soRecurringPurchaseDTO1).isNotEqualTo(soRecurringPurchaseDTO2);
        soRecurringPurchaseDTO2.setRpId(soRecurringPurchaseDTO1.getRpId());
        assertThat(soRecurringPurchaseDTO1).isEqualTo(soRecurringPurchaseDTO2);
        soRecurringPurchaseDTO2.setRpId(2L);
        assertThat(soRecurringPurchaseDTO1).isNotEqualTo(soRecurringPurchaseDTO2);
        soRecurringPurchaseDTO1.setRpId(null);
        assertThat(soRecurringPurchaseDTO1).isNotEqualTo(soRecurringPurchaseDTO2);
    }
}
