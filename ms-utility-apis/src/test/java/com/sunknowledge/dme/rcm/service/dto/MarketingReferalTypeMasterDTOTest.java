package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MarketingReferalTypeMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MarketingReferalTypeMasterDTO.class);
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO1 = new MarketingReferalTypeMasterDTO();
        marketingReferalTypeMasterDTO1.setMarketingReferralTypeId(1L);
        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO2 = new MarketingReferalTypeMasterDTO();
        assertThat(marketingReferalTypeMasterDTO1).isNotEqualTo(marketingReferalTypeMasterDTO2);
        marketingReferalTypeMasterDTO2.setMarketingReferralTypeId(marketingReferalTypeMasterDTO1.getMarketingReferralTypeId());
        assertThat(marketingReferalTypeMasterDTO1).isEqualTo(marketingReferalTypeMasterDTO2);
        marketingReferalTypeMasterDTO2.setMarketingReferralTypeId(2L);
        assertThat(marketingReferalTypeMasterDTO1).isNotEqualTo(marketingReferalTypeMasterDTO2);
        marketingReferalTypeMasterDTO1.setMarketingReferralTypeId(null);
        assertThat(marketingReferalTypeMasterDTO1).isNotEqualTo(marketingReferalTypeMasterDTO2);
    }
}
