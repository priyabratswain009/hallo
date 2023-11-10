package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AddressVerificationResponseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressVerificationResponseDTO.class);
        AddressVerificationResponseDTO addressVerificationResponseDTO1 = new AddressVerificationResponseDTO();
        addressVerificationResponseDTO1.setUspsAddressVerificationId(1L);
        AddressVerificationResponseDTO addressVerificationResponseDTO2 = new AddressVerificationResponseDTO();
        assertThat(addressVerificationResponseDTO1).isNotEqualTo(addressVerificationResponseDTO2);
        addressVerificationResponseDTO2.setUspsAddressVerificationId(addressVerificationResponseDTO1.getUspsAddressVerificationId());
        assertThat(addressVerificationResponseDTO1).isEqualTo(addressVerificationResponseDTO2);
        addressVerificationResponseDTO2.setUspsAddressVerificationId(2L);
        assertThat(addressVerificationResponseDTO1).isNotEqualTo(addressVerificationResponseDTO2);
        addressVerificationResponseDTO1.setUspsAddressVerificationId(null);
        assertThat(addressVerificationResponseDTO1).isNotEqualTo(addressVerificationResponseDTO2);
    }
}
