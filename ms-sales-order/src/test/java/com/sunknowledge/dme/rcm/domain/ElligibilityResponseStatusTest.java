package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElligibilityResponseStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElligibilityResponseStatus.class);
        ElligibilityResponseStatus elligibilityResponseStatus1 = new ElligibilityResponseStatus();
        elligibilityResponseStatus1.setElligibilityResponseStatusId(1L);
        ElligibilityResponseStatus elligibilityResponseStatus2 = new ElligibilityResponseStatus();
        elligibilityResponseStatus2.setElligibilityResponseStatusId(elligibilityResponseStatus1.getElligibilityResponseStatusId());
        assertThat(elligibilityResponseStatus1).isEqualTo(elligibilityResponseStatus2);
        elligibilityResponseStatus2.setElligibilityResponseStatusId(2L);
        assertThat(elligibilityResponseStatus1).isNotEqualTo(elligibilityResponseStatus2);
        elligibilityResponseStatus1.setElligibilityResponseStatusId(null);
        assertThat(elligibilityResponseStatus1).isNotEqualTo(elligibilityResponseStatus2);
    }
}
