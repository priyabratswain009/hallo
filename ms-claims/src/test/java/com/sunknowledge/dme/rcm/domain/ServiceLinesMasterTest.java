package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServiceLinesMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceLinesMaster.class);
        ServiceLinesMaster serviceLinesMaster1 = new ServiceLinesMaster();
        serviceLinesMaster1.setChangeHealthPrimarySubmisionServicelinesId(1L);
        ServiceLinesMaster serviceLinesMaster2 = new ServiceLinesMaster();
        serviceLinesMaster2.setChangeHealthPrimarySubmisionServicelinesId(
            serviceLinesMaster1.getChangeHealthPrimarySubmisionServicelinesId()
        );
        assertThat(serviceLinesMaster1).isEqualTo(serviceLinesMaster2);
        serviceLinesMaster2.setChangeHealthPrimarySubmisionServicelinesId(2L);
        assertThat(serviceLinesMaster1).isNotEqualTo(serviceLinesMaster2);
        serviceLinesMaster1.setChangeHealthPrimarySubmisionServicelinesId(null);
        assertThat(serviceLinesMaster1).isNotEqualTo(serviceLinesMaster2);
    }
}
