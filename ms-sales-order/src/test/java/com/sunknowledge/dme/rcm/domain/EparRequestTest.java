package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EparRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EparRequest.class);
        EparRequest eparRequest1 = new EparRequest();
        eparRequest1.setEparRequestId(1L);
        EparRequest eparRequest2 = new EparRequest();
        eparRequest2.setEparRequestId(eparRequest1.getEparRequestId());
        assertThat(eparRequest1).isEqualTo(eparRequest2);
        eparRequest2.setEparRequestId(2L);
        assertThat(eparRequest1).isNotEqualTo(eparRequest2);
        eparRequest1.setEparRequestId(null);
        assertThat(eparRequest1).isNotEqualTo(eparRequest2);
    }
}
