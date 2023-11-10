package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElligibilityResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElligibilityResponse.class);
        ElligibilityResponse elligibilityResponse1 = new ElligibilityResponse();
        elligibilityResponse1.setElligibilityResponseStatusId(1L);
        ElligibilityResponse elligibilityResponse2 = new ElligibilityResponse();
        elligibilityResponse2.setElligibilityResponseStatusId(elligibilityResponse1.getElligibilityResponseStatusId());
        assertThat(elligibilityResponse1).isEqualTo(elligibilityResponse2);
        elligibilityResponse2.setElligibilityResponseStatusId(2L);
        assertThat(elligibilityResponse1).isNotEqualTo(elligibilityResponse2);
        elligibilityResponse1.setElligibilityResponseStatusId(null);
        assertThat(elligibilityResponse1).isNotEqualTo(elligibilityResponse2);
    }
}
