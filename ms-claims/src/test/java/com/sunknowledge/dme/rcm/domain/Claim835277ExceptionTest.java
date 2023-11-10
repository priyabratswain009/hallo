package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Claim835277ExceptionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Claim835277Exception.class);
        Claim835277Exception claim835277Exception1 = new Claim835277Exception();
        claim835277Exception1.setExceptionId(1L);
        Claim835277Exception claim835277Exception2 = new Claim835277Exception();
        claim835277Exception2.setExceptionId(claim835277Exception1.getExceptionId());
        assertThat(claim835277Exception1).isEqualTo(claim835277Exception2);
        claim835277Exception2.setExceptionId(2L);
        assertThat(claim835277Exception1).isNotEqualTo(claim835277Exception2);
        claim835277Exception1.setExceptionId(null);
        assertThat(claim835277Exception1).isNotEqualTo(claim835277Exception2);
    }
}
