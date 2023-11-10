package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Claims835CrossoverExceptionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Claims835CrossoverExceptionDTO.class);
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO1 = new Claims835CrossoverExceptionDTO();
        claims835CrossoverExceptionDTO1.setClaims835CrossoverExceptionId(1L);
        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO2 = new Claims835CrossoverExceptionDTO();
        assertThat(claims835CrossoverExceptionDTO1).isNotEqualTo(claims835CrossoverExceptionDTO2);
        claims835CrossoverExceptionDTO2.setClaims835CrossoverExceptionId(
            claims835CrossoverExceptionDTO1.getClaims835CrossoverExceptionId()
        );
        assertThat(claims835CrossoverExceptionDTO1).isEqualTo(claims835CrossoverExceptionDTO2);
        claims835CrossoverExceptionDTO2.setClaims835CrossoverExceptionId(2L);
        assertThat(claims835CrossoverExceptionDTO1).isNotEqualTo(claims835CrossoverExceptionDTO2);
        claims835CrossoverExceptionDTO1.setClaims835CrossoverExceptionId(null);
        assertThat(claims835CrossoverExceptionDTO1).isNotEqualTo(claims835CrossoverExceptionDTO2);
    }
}
