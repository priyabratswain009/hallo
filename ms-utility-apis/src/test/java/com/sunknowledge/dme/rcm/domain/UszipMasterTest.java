package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UszipMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UszipMaster.class);
        UszipMaster uszipMaster1 = new UszipMaster();
        uszipMaster1.setUszipMasterId(1L);
        UszipMaster uszipMaster2 = new UszipMaster();
        uszipMaster2.setUszipMasterId(uszipMaster1.getUszipMasterId());
        assertThat(uszipMaster1).isEqualTo(uszipMaster2);
        uszipMaster2.setUszipMasterId(2L);
        assertThat(uszipMaster1).isNotEqualTo(uszipMaster2);
        uszipMaster1.setUszipMasterId(null);
        assertThat(uszipMaster1).isNotEqualTo(uszipMaster2);
    }
}
