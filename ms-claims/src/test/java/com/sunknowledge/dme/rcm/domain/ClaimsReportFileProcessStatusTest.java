package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimsReportFileProcessStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimsReportFileProcessStatus.class);
        ClaimsReportFileProcessStatus claimsReportFileProcessStatus1 = new ClaimsReportFileProcessStatus();
        claimsReportFileProcessStatus1.setFileStatusId(1L);
        ClaimsReportFileProcessStatus claimsReportFileProcessStatus2 = new ClaimsReportFileProcessStatus();
        claimsReportFileProcessStatus2.setFileStatusId(claimsReportFileProcessStatus1.getFileStatusId());
        assertThat(claimsReportFileProcessStatus1).isEqualTo(claimsReportFileProcessStatus2);
        claimsReportFileProcessStatus2.setFileStatusId(2L);
        assertThat(claimsReportFileProcessStatus1).isNotEqualTo(claimsReportFileProcessStatus2);
        claimsReportFileProcessStatus1.setFileStatusId(null);
        assertThat(claimsReportFileProcessStatus1).isNotEqualTo(claimsReportFileProcessStatus2);
    }
}
