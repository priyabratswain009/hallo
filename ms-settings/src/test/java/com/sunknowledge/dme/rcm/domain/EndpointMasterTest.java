package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EndpointMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EndpointMaster.class);
        EndpointMaster endpointMaster1 = new EndpointMaster();
        endpointMaster1.setEndpointId(1L);
        EndpointMaster endpointMaster2 = new EndpointMaster();
        endpointMaster2.setEndpointId(endpointMaster1.getEndpointId());
        assertThat(endpointMaster1).isEqualTo(endpointMaster2);
        endpointMaster2.setEndpointId(2L);
        assertThat(endpointMaster1).isNotEqualTo(endpointMaster2);
        endpointMaster1.setEndpointId(null);
        assertThat(endpointMaster1).isNotEqualTo(endpointMaster2);
    }
}
