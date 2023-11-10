package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchInsuranceMapAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchInsuranceMapAuditLogDTO.class);
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO1 = new BranchInsuranceMapAuditLogDTO();
        branchInsuranceMapAuditLogDTO1.setId(1L);
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO2 = new BranchInsuranceMapAuditLogDTO();
        assertThat(branchInsuranceMapAuditLogDTO1).isNotEqualTo(branchInsuranceMapAuditLogDTO2);
        branchInsuranceMapAuditLogDTO2.setId(branchInsuranceMapAuditLogDTO1.getId());
        assertThat(branchInsuranceMapAuditLogDTO1).isEqualTo(branchInsuranceMapAuditLogDTO2);
        branchInsuranceMapAuditLogDTO2.setId(2L);
        assertThat(branchInsuranceMapAuditLogDTO1).isNotEqualTo(branchInsuranceMapAuditLogDTO2);
        branchInsuranceMapAuditLogDTO1.setId(null);
        assertThat(branchInsuranceMapAuditLogDTO1).isNotEqualTo(branchInsuranceMapAuditLogDTO2);
    }
}
