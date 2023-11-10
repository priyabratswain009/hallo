package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchOfficeAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchOfficeAuditLogDTO.class);
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO1 = new BranchOfficeAuditLogDTO();
        branchOfficeAuditLogDTO1.setId(1L);
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO2 = new BranchOfficeAuditLogDTO();
        assertThat(branchOfficeAuditLogDTO1).isNotEqualTo(branchOfficeAuditLogDTO2);
        branchOfficeAuditLogDTO2.setId(branchOfficeAuditLogDTO1.getId());
        assertThat(branchOfficeAuditLogDTO1).isEqualTo(branchOfficeAuditLogDTO2);
        branchOfficeAuditLogDTO2.setId(2L);
        assertThat(branchOfficeAuditLogDTO1).isNotEqualTo(branchOfficeAuditLogDTO2);
        branchOfficeAuditLogDTO1.setId(null);
        assertThat(branchOfficeAuditLogDTO1).isNotEqualTo(branchOfficeAuditLogDTO2);
    }
}
