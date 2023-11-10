package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UspsAddressVerificationResponseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UspsAddressVerificationResponseDTO.class);
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO1 = new UspsAddressVerificationResponseDTO();
        uspsAddressVerificationResponseDTO1.setUspsAddressVerificationId(1L);
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO2 = new UspsAddressVerificationResponseDTO();
        assertThat(uspsAddressVerificationResponseDTO1).isNotEqualTo(uspsAddressVerificationResponseDTO2);
        uspsAddressVerificationResponseDTO2.setUspsAddressVerificationId(
            uspsAddressVerificationResponseDTO1.getUspsAddressVerificationId()
        );
        assertThat(uspsAddressVerificationResponseDTO1).isEqualTo(uspsAddressVerificationResponseDTO2);
        uspsAddressVerificationResponseDTO2.setUspsAddressVerificationId(2L);
        assertThat(uspsAddressVerificationResponseDTO1).isNotEqualTo(uspsAddressVerificationResponseDTO2);
        uspsAddressVerificationResponseDTO1.setUspsAddressVerificationId(null);
        assertThat(uspsAddressVerificationResponseDTO1).isNotEqualTo(uspsAddressVerificationResponseDTO2);
    }
}
