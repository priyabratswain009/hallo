package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsReportFileProcessStatusDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsReportFileProcessStatusDTO.class);
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO1 = new ClaimsReportFileProcessStatusDTO();
        claimsReportFileProcessStatusDTO1.setFileStatusId(1L);
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO2 = new ClaimsReportFileProcessStatusDTO();
        assertThat(claimsReportFileProcessStatusDTO1).isNotEqualTo(claimsReportFileProcessStatusDTO2);
        claimsReportFileProcessStatusDTO2.setFileStatusId(claimsReportFileProcessStatusDTO1.getFileStatusId());
        assertThat(claimsReportFileProcessStatusDTO1).isEqualTo(claimsReportFileProcessStatusDTO2);
        claimsReportFileProcessStatusDTO2.setFileStatusId(2L);
        assertThat(claimsReportFileProcessStatusDTO1).isNotEqualTo(claimsReportFileProcessStatusDTO2);
        claimsReportFileProcessStatusDTO1.setFileStatusId(null);
        assertThat(claimsReportFileProcessStatusDTO1).isNotEqualTo(claimsReportFileProcessStatusDTO2);
    }
}
