package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EndpointMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EndpointMasterDTO.class);
        EndpointMasterDTO endpointMasterDTO1 = new EndpointMasterDTO();
        endpointMasterDTO1.setEndpointId(1L);
        EndpointMasterDTO endpointMasterDTO2 = new EndpointMasterDTO();
        assertThat(endpointMasterDTO1).isNotEqualTo(endpointMasterDTO2);
        endpointMasterDTO2.setEndpointId(endpointMasterDTO1.getEndpointId());
        assertThat(endpointMasterDTO1).isEqualTo(endpointMasterDTO2);
        endpointMasterDTO2.setEndpointId(2L);
        assertThat(endpointMasterDTO1).isNotEqualTo(endpointMasterDTO2);
        endpointMasterDTO1.setEndpointId(null);
        assertThat(endpointMasterDTO1).isNotEqualTo(endpointMasterDTO2);
    }
}
