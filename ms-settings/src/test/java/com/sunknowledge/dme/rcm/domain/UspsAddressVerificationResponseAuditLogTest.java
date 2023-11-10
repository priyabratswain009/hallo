package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UspsAddressVerificationResponseAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UspsAddressVerificationResponseAuditLog.class);
        UspsAddressVerificationResponseAuditLog uspsAddressVerificationResponseAuditLog1 = new UspsAddressVerificationResponseAuditLog();
        uspsAddressVerificationResponseAuditLog1.setId(1L);
        UspsAddressVerificationResponseAuditLog uspsAddressVerificationResponseAuditLog2 = new UspsAddressVerificationResponseAuditLog();
        uspsAddressVerificationResponseAuditLog2.setId(uspsAddressVerificationResponseAuditLog1.getId());
        assertThat(uspsAddressVerificationResponseAuditLog1).isEqualTo(uspsAddressVerificationResponseAuditLog2);
        uspsAddressVerificationResponseAuditLog2.setId(2L);
        assertThat(uspsAddressVerificationResponseAuditLog1).isNotEqualTo(uspsAddressVerificationResponseAuditLog2);
        uspsAddressVerificationResponseAuditLog1.setId(null);
        assertThat(uspsAddressVerificationResponseAuditLog1).isNotEqualTo(uspsAddressVerificationResponseAuditLog2);
    }
}
