package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FunctionalAbilityDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunctionalAbilityDTO.class);
        FunctionalAbilityDTO functionalAbilityDTO1 = new FunctionalAbilityDTO();
        functionalAbilityDTO1.setFunctionalAbilityId(1L);
        FunctionalAbilityDTO functionalAbilityDTO2 = new FunctionalAbilityDTO();
        assertThat(functionalAbilityDTO1).isNotEqualTo(functionalAbilityDTO2);
        functionalAbilityDTO2.setFunctionalAbilityId(functionalAbilityDTO1.getFunctionalAbilityId());
        assertThat(functionalAbilityDTO1).isEqualTo(functionalAbilityDTO2);
        functionalAbilityDTO2.setFunctionalAbilityId(2L);
        assertThat(functionalAbilityDTO1).isNotEqualTo(functionalAbilityDTO2);
        functionalAbilityDTO1.setFunctionalAbilityId(null);
        assertThat(functionalAbilityDTO1).isNotEqualTo(functionalAbilityDTO2);
    }
}
