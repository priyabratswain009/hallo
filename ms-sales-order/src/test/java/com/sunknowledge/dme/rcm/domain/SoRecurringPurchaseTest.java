package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SoRecurringPurchaseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoRecurringPurchase.class);
        SoRecurringPurchase soRecurringPurchase1 = new SoRecurringPurchase();
        soRecurringPurchase1.setRpId(1L);
        SoRecurringPurchase soRecurringPurchase2 = new SoRecurringPurchase();
        soRecurringPurchase2.setRpId(soRecurringPurchase1.getRpId());
        assertThat(soRecurringPurchase1).isEqualTo(soRecurringPurchase2);
        soRecurringPurchase2.setRpId(2L);
        assertThat(soRecurringPurchase1).isNotEqualTo(soRecurringPurchase2);
        soRecurringPurchase1.setRpId(null);
        assertThat(soRecurringPurchase1).isNotEqualTo(soRecurringPurchase2);
    }
}
