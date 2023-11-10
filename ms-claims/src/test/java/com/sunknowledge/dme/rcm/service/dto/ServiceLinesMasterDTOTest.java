package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServiceLinesMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceLinesMasterDTO.class);
        ServiceLinesMasterDTO serviceLinesMasterDTO1 = new ServiceLinesMasterDTO();
        serviceLinesMasterDTO1.setChangeHealthPrimarySubmisionServicelinesId(1L);
        ServiceLinesMasterDTO serviceLinesMasterDTO2 = new ServiceLinesMasterDTO();
        assertThat(serviceLinesMasterDTO1).isNotEqualTo(serviceLinesMasterDTO2);
        serviceLinesMasterDTO2.setChangeHealthPrimarySubmisionServicelinesId(
            serviceLinesMasterDTO1.getChangeHealthPrimarySubmisionServicelinesId()
        );
        assertThat(serviceLinesMasterDTO1).isEqualTo(serviceLinesMasterDTO2);
        serviceLinesMasterDTO2.setChangeHealthPrimarySubmisionServicelinesId(2L);
        assertThat(serviceLinesMasterDTO1).isNotEqualTo(serviceLinesMasterDTO2);
        serviceLinesMasterDTO1.setChangeHealthPrimarySubmisionServicelinesId(null);
        assertThat(serviceLinesMasterDTO1).isNotEqualTo(serviceLinesMasterDTO2);
    }
}
