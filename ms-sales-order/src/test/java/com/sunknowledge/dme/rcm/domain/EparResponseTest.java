package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EparResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EparResponse.class);
        EparResponse eparResponse1 = new EparResponse();
        eparResponse1.setEparResponseId(1L);
        EparResponse eparResponse2 = new EparResponse();
        eparResponse2.setEparResponseId(eparResponse1.getEparResponseId());
        assertThat(eparResponse1).isEqualTo(eparResponse2);
        eparResponse2.setEparResponseId(2L);
        assertThat(eparResponse1).isNotEqualTo(eparResponse2);
        eparResponse1.setEparResponseId(null);
        assertThat(eparResponse1).isNotEqualTo(eparResponse2);
    }
}
