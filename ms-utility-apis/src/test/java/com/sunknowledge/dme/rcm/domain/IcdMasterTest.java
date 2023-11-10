package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IcdMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IcdMaster.class);
        IcdMaster icdMaster1 = new IcdMaster();
        icdMaster1.setIcdMasterId(1L);
        IcdMaster icdMaster2 = new IcdMaster();
        icdMaster2.setIcdMasterId(icdMaster1.getIcdMasterId());
        assertThat(icdMaster1).isEqualTo(icdMaster2);
        icdMaster2.setIcdMasterId(2L);
        assertThat(icdMaster1).isNotEqualTo(icdMaster2);
        icdMaster1.setIcdMasterId(null);
        assertThat(icdMaster1).isNotEqualTo(icdMaster2);
    }
}
