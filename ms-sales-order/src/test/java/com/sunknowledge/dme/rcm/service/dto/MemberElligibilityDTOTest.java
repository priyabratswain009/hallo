package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MemberElligibilityDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberElligibilityDTO.class);
        MemberElligibilityDTO memberElligibilityDTO1 = new MemberElligibilityDTO();
        memberElligibilityDTO1.setClaimElligibilityMasterId(1L);
        MemberElligibilityDTO memberElligibilityDTO2 = new MemberElligibilityDTO();
        assertThat(memberElligibilityDTO1).isNotEqualTo(memberElligibilityDTO2);
        memberElligibilityDTO2.setClaimElligibilityMasterId(memberElligibilityDTO1.getClaimElligibilityMasterId());
        assertThat(memberElligibilityDTO1).isEqualTo(memberElligibilityDTO2);
        memberElligibilityDTO2.setClaimElligibilityMasterId(2L);
        assertThat(memberElligibilityDTO1).isNotEqualTo(memberElligibilityDTO2);
        memberElligibilityDTO1.setClaimElligibilityMasterId(null);
        assertThat(memberElligibilityDTO1).isNotEqualTo(memberElligibilityDTO2);
    }
}
