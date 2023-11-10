package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryClaimSubmisionMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryClaimSubmisionMasterAuditLogDTO.class);
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO1 = new SecondaryClaimSubmisionMasterAuditLogDTO();
        secondaryClaimSubmisionMasterAuditLogDTO1.setChgHealthSconarySubmnMsterId(1L);
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO2 = new SecondaryClaimSubmisionMasterAuditLogDTO();
        assertThat(secondaryClaimSubmisionMasterAuditLogDTO1).isNotEqualTo(secondaryClaimSubmisionMasterAuditLogDTO2);
        secondaryClaimSubmisionMasterAuditLogDTO2.setChgHealthSconarySubmnMsterId(
            secondaryClaimSubmisionMasterAuditLogDTO1.getChgHealthSconarySubmnMsterId()
        );
        assertThat(secondaryClaimSubmisionMasterAuditLogDTO1).isEqualTo(secondaryClaimSubmisionMasterAuditLogDTO2);
        secondaryClaimSubmisionMasterAuditLogDTO2.setChgHealthSconarySubmnMsterId(2L);
        assertThat(secondaryClaimSubmisionMasterAuditLogDTO1).isNotEqualTo(secondaryClaimSubmisionMasterAuditLogDTO2);
        secondaryClaimSubmisionMasterAuditLogDTO1.setChgHealthSconarySubmnMsterId(null);
        assertThat(secondaryClaimSubmisionMasterAuditLogDTO1).isNotEqualTo(secondaryClaimSubmisionMasterAuditLogDTO2);
    }
}
