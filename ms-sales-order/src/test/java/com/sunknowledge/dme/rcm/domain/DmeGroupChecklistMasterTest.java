package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DmeGroupChecklistMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmeGroupChecklistMaster.class);
        DmeGroupChecklistMaster dmeGroupChecklistMaster1 = new DmeGroupChecklistMaster();
        dmeGroupChecklistMaster1.setDmeGroupChecklistId(1L);
        DmeGroupChecklistMaster dmeGroupChecklistMaster2 = new DmeGroupChecklistMaster();
        dmeGroupChecklistMaster2.setDmeGroupChecklistId(dmeGroupChecklistMaster1.getDmeGroupChecklistId());
        assertThat(dmeGroupChecklistMaster1).isEqualTo(dmeGroupChecklistMaster2);
        dmeGroupChecklistMaster2.setDmeGroupChecklistId(2L);
        assertThat(dmeGroupChecklistMaster1).isNotEqualTo(dmeGroupChecklistMaster2);
        dmeGroupChecklistMaster1.setDmeGroupChecklistId(null);
        assertThat(dmeGroupChecklistMaster1).isNotEqualTo(dmeGroupChecklistMaster2);
    }
}
