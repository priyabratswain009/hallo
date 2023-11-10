package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryClaimSubmisionMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryClaimSubmisionMasterAuditLog.class);
        SecondaryClaimSubmisionMasterAuditLog secondaryClaimSubmisionMasterAuditLog1 = new SecondaryClaimSubmisionMasterAuditLog();
        secondaryClaimSubmisionMasterAuditLog1.setChgHealthSconarySubmnMsterId(1L);
        SecondaryClaimSubmisionMasterAuditLog secondaryClaimSubmisionMasterAuditLog2 = new SecondaryClaimSubmisionMasterAuditLog();
        secondaryClaimSubmisionMasterAuditLog2.setChgHealthSconarySubmnMsterId(
            secondaryClaimSubmisionMasterAuditLog1.getChgHealthSconarySubmnMsterId()
        );
        assertThat(secondaryClaimSubmisionMasterAuditLog1).isEqualTo(secondaryClaimSubmisionMasterAuditLog2);
        secondaryClaimSubmisionMasterAuditLog2.setChgHealthSconarySubmnMsterId(2L);
        assertThat(secondaryClaimSubmisionMasterAuditLog1).isNotEqualTo(secondaryClaimSubmisionMasterAuditLog2);
        secondaryClaimSubmisionMasterAuditLog1.setChgHealthSconarySubmnMsterId(null);
        assertThat(secondaryClaimSubmisionMasterAuditLog1).isNotEqualTo(secondaryClaimSubmisionMasterAuditLog2);
    }
}
