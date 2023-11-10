package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WipQueueDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WipQueueDetails.class);
        WipQueueDetails wipQueueDetails1 = new WipQueueDetails();
        wipQueueDetails1.setWipQueueDetailsId(1L);
        WipQueueDetails wipQueueDetails2 = new WipQueueDetails();
        wipQueueDetails2.setWipQueueDetailsId(wipQueueDetails1.getWipQueueDetailsId());
        assertThat(wipQueueDetails1).isEqualTo(wipQueueDetails2);
        wipQueueDetails2.setWipQueueDetailsId(2L);
        assertThat(wipQueueDetails1).isNotEqualTo(wipQueueDetails2);
        wipQueueDetails1.setWipQueueDetailsId(null);
        assertThat(wipQueueDetails1).isNotEqualTo(wipQueueDetails2);
    }
}
