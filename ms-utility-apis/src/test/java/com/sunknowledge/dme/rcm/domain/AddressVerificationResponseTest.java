package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AddressVerificationResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressVerificationResponse.class);
        AddressVerificationResponse addressVerificationResponse1 = new AddressVerificationResponse();
        addressVerificationResponse1.setUspsAddressVerificationId(1L);
        AddressVerificationResponse addressVerificationResponse2 = new AddressVerificationResponse();
        addressVerificationResponse2.setUspsAddressVerificationId(addressVerificationResponse1.getUspsAddressVerificationId());
        assertThat(addressVerificationResponse1).isEqualTo(addressVerificationResponse2);
        addressVerificationResponse2.setUspsAddressVerificationId(2L);
        assertThat(addressVerificationResponse1).isNotEqualTo(addressVerificationResponse2);
        addressVerificationResponse1.setUspsAddressVerificationId(null);
        assertThat(addressVerificationResponse1).isNotEqualTo(addressVerificationResponse2);
    }
}
