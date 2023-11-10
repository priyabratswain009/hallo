package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FunctionalAbilityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunctionalAbility.class);
        FunctionalAbility functionalAbility1 = new FunctionalAbility();
        functionalAbility1.setFunctionalAbilityId(1L);
        FunctionalAbility functionalAbility2 = new FunctionalAbility();
        functionalAbility2.setFunctionalAbilityId(functionalAbility1.getFunctionalAbilityId());
        assertThat(functionalAbility1).isEqualTo(functionalAbility2);
        functionalAbility2.setFunctionalAbilityId(2L);
        assertThat(functionalAbility1).isNotEqualTo(functionalAbility2);
        functionalAbility1.setFunctionalAbilityId(null);
        assertThat(functionalAbility1).isNotEqualTo(functionalAbility2);
    }
}
