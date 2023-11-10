package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Claims835CrossoverProcessedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Claims835CrossoverProcessedDTO.class);
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO1 = new Claims835CrossoverProcessedDTO();
        claims835CrossoverProcessedDTO1.setClaims835CrossoverProcessedId(1L);
        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO2 = new Claims835CrossoverProcessedDTO();
        assertThat(claims835CrossoverProcessedDTO1).isNotEqualTo(claims835CrossoverProcessedDTO2);
        claims835CrossoverProcessedDTO2.setClaims835CrossoverProcessedId(
            claims835CrossoverProcessedDTO1.getClaims835CrossoverProcessedId()
        );
        assertThat(claims835CrossoverProcessedDTO1).isEqualTo(claims835CrossoverProcessedDTO2);
        claims835CrossoverProcessedDTO2.setClaims835CrossoverProcessedId(2L);
        assertThat(claims835CrossoverProcessedDTO1).isNotEqualTo(claims835CrossoverProcessedDTO2);
        claims835CrossoverProcessedDTO1.setClaims835CrossoverProcessedId(null);
        assertThat(claims835CrossoverProcessedDTO1).isNotEqualTo(claims835CrossoverProcessedDTO2);
    }
}
