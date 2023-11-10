package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimFormMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimFormMaster.class);
        ClaimFormMaster claimFormMaster1 = new ClaimFormMaster();
        claimFormMaster1.setClaimFormId(1L);
        ClaimFormMaster claimFormMaster2 = new ClaimFormMaster();
        claimFormMaster2.setClaimFormId(claimFormMaster1.getClaimFormId());
        assertThat(claimFormMaster1).isEqualTo(claimFormMaster2);
        claimFormMaster2.setClaimFormId(2L);
        assertThat(claimFormMaster1).isNotEqualTo(claimFormMaster2);
        claimFormMaster1.setClaimFormId(null);
        assertThat(claimFormMaster1).isNotEqualTo(claimFormMaster2);
    }
}
