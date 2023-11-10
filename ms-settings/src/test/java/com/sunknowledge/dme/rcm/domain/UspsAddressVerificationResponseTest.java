package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UspsAddressVerificationResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UspsAddressVerificationResponse.class);
        UspsAddressVerificationResponse uspsAddressVerificationResponse1 = new UspsAddressVerificationResponse();
        uspsAddressVerificationResponse1.setUspsAddressVerificationId(1L);
        UspsAddressVerificationResponse uspsAddressVerificationResponse2 = new UspsAddressVerificationResponse();
        uspsAddressVerificationResponse2.setUspsAddressVerificationId(uspsAddressVerificationResponse1.getUspsAddressVerificationId());
        assertThat(uspsAddressVerificationResponse1).isEqualTo(uspsAddressVerificationResponse2);
        uspsAddressVerificationResponse2.setUspsAddressVerificationId(2L);
        assertThat(uspsAddressVerificationResponse1).isNotEqualTo(uspsAddressVerificationResponse2);
        uspsAddressVerificationResponse1.setUspsAddressVerificationId(null);
        assertThat(uspsAddressVerificationResponse1).isNotEqualTo(uspsAddressVerificationResponse2);
    }
}
