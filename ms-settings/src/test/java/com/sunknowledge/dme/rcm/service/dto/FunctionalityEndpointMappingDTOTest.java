package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FunctionalityEndpointMappingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunctionalityEndpointMappingDTO.class);
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO1 = new FunctionalityEndpointMappingDTO();
        functionalityEndpointMappingDTO1.setFunctionalityEndpointMappingId(1L);
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO2 = new FunctionalityEndpointMappingDTO();
        assertThat(functionalityEndpointMappingDTO1).isNotEqualTo(functionalityEndpointMappingDTO2);
        functionalityEndpointMappingDTO2.setFunctionalityEndpointMappingId(
            functionalityEndpointMappingDTO1.getFunctionalityEndpointMappingId()
        );
        assertThat(functionalityEndpointMappingDTO1).isEqualTo(functionalityEndpointMappingDTO2);
        functionalityEndpointMappingDTO2.setFunctionalityEndpointMappingId(2L);
        assertThat(functionalityEndpointMappingDTO1).isNotEqualTo(functionalityEndpointMappingDTO2);
        functionalityEndpointMappingDTO1.setFunctionalityEndpointMappingId(null);
        assertThat(functionalityEndpointMappingDTO1).isNotEqualTo(functionalityEndpointMappingDTO2);
    }
}
