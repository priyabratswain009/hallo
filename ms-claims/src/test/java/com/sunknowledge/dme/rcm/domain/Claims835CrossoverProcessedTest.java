package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Claims835CrossoverProcessedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Claims835CrossoverProcessed.class);
        Claims835CrossoverProcessed claims835CrossoverProcessed1 = new Claims835CrossoverProcessed();
        claims835CrossoverProcessed1.setClaims835CrossoverProcessedId(1L);
        Claims835CrossoverProcessed claims835CrossoverProcessed2 = new Claims835CrossoverProcessed();
        claims835CrossoverProcessed2.setClaims835CrossoverProcessedId(claims835CrossoverProcessed1.getClaims835CrossoverProcessedId());
        assertThat(claims835CrossoverProcessed1).isEqualTo(claims835CrossoverProcessed2);
        claims835CrossoverProcessed2.setClaims835CrossoverProcessedId(2L);
        assertThat(claims835CrossoverProcessed1).isNotEqualTo(claims835CrossoverProcessed2);
        claims835CrossoverProcessed1.setClaims835CrossoverProcessedId(null);
        assertThat(claims835CrossoverProcessed1).isNotEqualTo(claims835CrossoverProcessed2);
    }
}
