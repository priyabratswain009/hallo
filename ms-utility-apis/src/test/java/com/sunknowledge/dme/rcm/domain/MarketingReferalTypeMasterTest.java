package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MarketingReferalTypeMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MarketingReferalTypeMaster.class);
        MarketingReferalTypeMaster marketingReferalTypeMaster1 = new MarketingReferalTypeMaster();
        marketingReferalTypeMaster1.setMarketingReferralTypeId(1L);
        MarketingReferalTypeMaster marketingReferalTypeMaster2 = new MarketingReferalTypeMaster();
        marketingReferalTypeMaster2.setMarketingReferralTypeId(marketingReferalTypeMaster1.getMarketingReferralTypeId());
        assertThat(marketingReferalTypeMaster1).isEqualTo(marketingReferalTypeMaster2);
        marketingReferalTypeMaster2.setMarketingReferralTypeId(2L);
        assertThat(marketingReferalTypeMaster1).isNotEqualTo(marketingReferalTypeMaster2);
        marketingReferalTypeMaster1.setMarketingReferralTypeId(null);
        assertThat(marketingReferalTypeMaster1).isNotEqualTo(marketingReferalTypeMaster2);
    }
}
