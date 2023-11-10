package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StateMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StateMaster.class);
        StateMaster stateMaster1 = new StateMaster();
        stateMaster1.setStateId(1L);
        StateMaster stateMaster2 = new StateMaster();
        stateMaster2.setStateId(stateMaster1.getStateId());
        assertThat(stateMaster1).isEqualTo(stateMaster2);
        stateMaster2.setStateId(2L);
        assertThat(stateMaster1).isNotEqualTo(stateMaster2);
        stateMaster1.setStateId(null);
        assertThat(stateMaster1).isNotEqualTo(stateMaster2);
    }
}
