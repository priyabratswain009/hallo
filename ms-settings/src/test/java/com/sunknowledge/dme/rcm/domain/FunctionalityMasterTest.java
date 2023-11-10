package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FunctionalityMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunctionalityMaster.class);
        FunctionalityMaster functionalityMaster1 = new FunctionalityMaster();
        functionalityMaster1.setFunctionalityId(1L);
        FunctionalityMaster functionalityMaster2 = new FunctionalityMaster();
        functionalityMaster2.setFunctionalityId(functionalityMaster1.getFunctionalityId());
        assertThat(functionalityMaster1).isEqualTo(functionalityMaster2);
        functionalityMaster2.setFunctionalityId(2L);
        assertThat(functionalityMaster1).isNotEqualTo(functionalityMaster2);
        functionalityMaster1.setFunctionalityId(null);
        assertThat(functionalityMaster1).isNotEqualTo(functionalityMaster2);
    }
}
