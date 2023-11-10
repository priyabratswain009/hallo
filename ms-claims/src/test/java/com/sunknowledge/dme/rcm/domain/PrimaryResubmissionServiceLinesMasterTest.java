package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryResubmissionServiceLinesMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryResubmissionServiceLinesMaster.class);
        PrimaryResubmissionServiceLinesMaster primaryResubmissionServiceLinesMaster1 = new PrimaryResubmissionServiceLinesMaster();
        primaryResubmissionServiceLinesMaster1.setChangeHealthPrimaryResubmisionServicelinesId(1L);
        PrimaryResubmissionServiceLinesMaster primaryResubmissionServiceLinesMaster2 = new PrimaryResubmissionServiceLinesMaster();
        primaryResubmissionServiceLinesMaster2.setChangeHealthPrimaryResubmisionServicelinesId(
            primaryResubmissionServiceLinesMaster1.getChangeHealthPrimaryResubmisionServicelinesId()
        );
        assertThat(primaryResubmissionServiceLinesMaster1).isEqualTo(primaryResubmissionServiceLinesMaster2);
        primaryResubmissionServiceLinesMaster2.setChangeHealthPrimaryResubmisionServicelinesId(2L);
        assertThat(primaryResubmissionServiceLinesMaster1).isNotEqualTo(primaryResubmissionServiceLinesMaster2);
        primaryResubmissionServiceLinesMaster1.setChangeHealthPrimaryResubmisionServicelinesId(null);
        assertThat(primaryResubmissionServiceLinesMaster1).isNotEqualTo(primaryResubmissionServiceLinesMaster2);
    }
}
