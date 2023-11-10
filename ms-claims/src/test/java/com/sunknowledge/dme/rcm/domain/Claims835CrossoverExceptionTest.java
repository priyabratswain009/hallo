package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Claims835CrossoverExceptionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Claims835CrossoverException.class);
        Claims835CrossoverException claims835CrossoverException1 = new Claims835CrossoverException();
        claims835CrossoverException1.setClaims835CrossoverExceptionId(1L);
        Claims835CrossoverException claims835CrossoverException2 = new Claims835CrossoverException();
        claims835CrossoverException2.setClaims835CrossoverExceptionId(claims835CrossoverException1.getClaims835CrossoverExceptionId());
        assertThat(claims835CrossoverException1).isEqualTo(claims835CrossoverException2);
        claims835CrossoverException2.setClaims835CrossoverExceptionId(2L);
        assertThat(claims835CrossoverException1).isNotEqualTo(claims835CrossoverException2);
        claims835CrossoverException1.setClaims835CrossoverExceptionId(null);
        assertThat(claims835CrossoverException1).isNotEqualTo(claims835CrossoverException2);
    }
}
