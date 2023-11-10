package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsStatus277DetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsStatus277Details.class);
        ClaimsStatus277Details claimsStatus277Details1 = new ClaimsStatus277Details();
        claimsStatus277Details1.setClaimStatus277DetailId(1L);
        ClaimsStatus277Details claimsStatus277Details2 = new ClaimsStatus277Details();
        claimsStatus277Details2.setClaimStatus277DetailId(claimsStatus277Details1.getClaimStatus277DetailId());
        assertThat(claimsStatus277Details1).isEqualTo(claimsStatus277Details2);
        claimsStatus277Details2.setClaimStatus277DetailId(2L);
        assertThat(claimsStatus277Details1).isNotEqualTo(claimsStatus277Details2);
        claimsStatus277Details1.setClaimStatus277DetailId(null);
        assertThat(claimsStatus277Details1).isNotEqualTo(claimsStatus277Details2);
    }
}
