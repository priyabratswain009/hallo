package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FunctionalityEndpointMappingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunctionalityEndpointMapping.class);
        FunctionalityEndpointMapping functionalityEndpointMapping1 = new FunctionalityEndpointMapping();
        functionalityEndpointMapping1.setFunctionalityEndpointMappingId(1L);
        FunctionalityEndpointMapping functionalityEndpointMapping2 = new FunctionalityEndpointMapping();
        functionalityEndpointMapping2.setFunctionalityEndpointMappingId(functionalityEndpointMapping1.getFunctionalityEndpointMappingId());
        assertThat(functionalityEndpointMapping1).isEqualTo(functionalityEndpointMapping2);
        functionalityEndpointMapping2.setFunctionalityEndpointMappingId(2L);
        assertThat(functionalityEndpointMapping1).isNotEqualTo(functionalityEndpointMapping2);
        functionalityEndpointMapping1.setFunctionalityEndpointMappingId(null);
        assertThat(functionalityEndpointMapping1).isNotEqualTo(functionalityEndpointMapping2);
    }
}
