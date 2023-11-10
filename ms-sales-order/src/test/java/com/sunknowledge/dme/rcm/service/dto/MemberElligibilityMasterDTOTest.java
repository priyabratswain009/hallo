package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MemberElligibilityMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberElligibilityMasterDTO.class);
        MemberElligibilityMasterDTO memberElligibilityMasterDTO1 = new MemberElligibilityMasterDTO();
        memberElligibilityMasterDTO1.setClaimElligibilityMasterId(1L);
        MemberElligibilityMasterDTO memberElligibilityMasterDTO2 = new MemberElligibilityMasterDTO();
        assertThat(memberElligibilityMasterDTO1).isNotEqualTo(memberElligibilityMasterDTO2);
        memberElligibilityMasterDTO2.setClaimElligibilityMasterId(memberElligibilityMasterDTO1.getClaimElligibilityMasterId());
        assertThat(memberElligibilityMasterDTO1).isEqualTo(memberElligibilityMasterDTO2);
        memberElligibilityMasterDTO2.setClaimElligibilityMasterId(2L);
        assertThat(memberElligibilityMasterDTO1).isNotEqualTo(memberElligibilityMasterDTO2);
        memberElligibilityMasterDTO1.setClaimElligibilityMasterId(null);
        assertThat(memberElligibilityMasterDTO1).isNotEqualTo(memberElligibilityMasterDTO2);
    }
}
