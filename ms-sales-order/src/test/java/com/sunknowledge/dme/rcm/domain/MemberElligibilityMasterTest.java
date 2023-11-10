package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MemberElligibilityMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberElligibilityMaster.class);
        MemberElligibilityMaster memberElligibilityMaster1 = new MemberElligibilityMaster();
        memberElligibilityMaster1.setClaimElligibilityMasterId(1L);
        MemberElligibilityMaster memberElligibilityMaster2 = new MemberElligibilityMaster();
        memberElligibilityMaster2.setClaimElligibilityMasterId(memberElligibilityMaster1.getClaimElligibilityMasterId());
        assertThat(memberElligibilityMaster1).isEqualTo(memberElligibilityMaster2);
        memberElligibilityMaster2.setClaimElligibilityMasterId(2L);
        assertThat(memberElligibilityMaster1).isNotEqualTo(memberElligibilityMaster2);
        memberElligibilityMaster1.setClaimElligibilityMasterId(null);
        assertThat(memberElligibilityMaster1).isNotEqualTo(memberElligibilityMaster2);
    }
}
