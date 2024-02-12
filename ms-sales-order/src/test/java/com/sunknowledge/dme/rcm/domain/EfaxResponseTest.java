package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EfaxResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EfaxResponse.class);
        EfaxResponse efaxResponse1 = new EfaxResponse();
        efaxResponse1.setEfaxResponseId(1L);
        EfaxResponse efaxResponse2 = new EfaxResponse();
        efaxResponse2.setEfaxResponseId(efaxResponse1.getEfaxResponseId());
        assertThat(efaxResponse1).isEqualTo(efaxResponse2);
        efaxResponse2.setEfaxResponseId(2L);
        assertThat(efaxResponse1).isNotEqualTo(efaxResponse2);
        efaxResponse1.setEfaxResponseId(null);
        assertThat(efaxResponse1).isNotEqualTo(efaxResponse2);
    }
}
