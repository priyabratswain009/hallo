package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HcpcsDmeGroupMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HcpcsDmeGroupMaster.class);
        HcpcsDmeGroupMaster hcpcsDmeGroupMaster1 = new HcpcsDmeGroupMaster();
        hcpcsDmeGroupMaster1.setHcpcsDmeId(1L);
        HcpcsDmeGroupMaster hcpcsDmeGroupMaster2 = new HcpcsDmeGroupMaster();
        hcpcsDmeGroupMaster2.setHcpcsDmeId(hcpcsDmeGroupMaster1.getHcpcsDmeId());
        assertThat(hcpcsDmeGroupMaster1).isEqualTo(hcpcsDmeGroupMaster2);
        hcpcsDmeGroupMaster2.setHcpcsDmeId(2L);
        assertThat(hcpcsDmeGroupMaster1).isNotEqualTo(hcpcsDmeGroupMaster2);
        hcpcsDmeGroupMaster1.setHcpcsDmeId(null);
        assertThat(hcpcsDmeGroupMaster1).isNotEqualTo(hcpcsDmeGroupMaster2);
    }
}
