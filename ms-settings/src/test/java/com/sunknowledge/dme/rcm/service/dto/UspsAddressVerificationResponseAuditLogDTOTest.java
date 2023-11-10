package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UspsAddressVerificationResponseAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UspsAddressVerificationResponseAuditLogDTO.class);
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO1 = new UspsAddressVerificationResponseAuditLogDTO();
        uspsAddressVerificationResponseAuditLogDTO1.setId(1L);
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO2 = new UspsAddressVerificationResponseAuditLogDTO();
        assertThat(uspsAddressVerificationResponseAuditLogDTO1).isNotEqualTo(uspsAddressVerificationResponseAuditLogDTO2);
        uspsAddressVerificationResponseAuditLogDTO2.setId(uspsAddressVerificationResponseAuditLogDTO1.getId());
        assertThat(uspsAddressVerificationResponseAuditLogDTO1).isEqualTo(uspsAddressVerificationResponseAuditLogDTO2);
        uspsAddressVerificationResponseAuditLogDTO2.setId(2L);
        assertThat(uspsAddressVerificationResponseAuditLogDTO1).isNotEqualTo(uspsAddressVerificationResponseAuditLogDTO2);
        uspsAddressVerificationResponseAuditLogDTO1.setId(null);
        assertThat(uspsAddressVerificationResponseAuditLogDTO1).isNotEqualTo(uspsAddressVerificationResponseAuditLogDTO2);
    }
}
