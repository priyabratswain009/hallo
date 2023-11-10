package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WipStatusMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WipStatusMaster.class);
        WipStatusMaster wipStatusMaster1 = new WipStatusMaster();
        wipStatusMaster1.setWipStatusId(1L);
        WipStatusMaster wipStatusMaster2 = new WipStatusMaster();
        wipStatusMaster2.setWipStatusId(wipStatusMaster1.getWipStatusId());
        assertThat(wipStatusMaster1).isEqualTo(wipStatusMaster2);
        wipStatusMaster2.setWipStatusId(2L);
        assertThat(wipStatusMaster1).isNotEqualTo(wipStatusMaster2);
        wipStatusMaster1.setWipStatusId(null);
        assertThat(wipStatusMaster1).isNotEqualTo(wipStatusMaster2);
    }
}
