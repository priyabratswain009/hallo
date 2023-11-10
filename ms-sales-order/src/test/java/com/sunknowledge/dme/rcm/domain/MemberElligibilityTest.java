package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MemberElligibilityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberElligibility.class);
        MemberElligibility memberElligibility1 = new MemberElligibility();
        memberElligibility1.setClaimElligibilityMasterId(1L);
        MemberElligibility memberElligibility2 = new MemberElligibility();
        memberElligibility2.setClaimElligibilityMasterId(memberElligibility1.getClaimElligibilityMasterId());
        assertThat(memberElligibility1).isEqualTo(memberElligibility2);
        memberElligibility2.setClaimElligibilityMasterId(2L);
        assertThat(memberElligibility1).isNotEqualTo(memberElligibility2);
        memberElligibility1.setClaimElligibilityMasterId(null);
        assertThat(memberElligibility1).isNotEqualTo(memberElligibility2);
    }
}
