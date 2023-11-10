package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrimaryResubmissionServiceLinesMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrimaryResubmissionServiceLinesMasterDTO.class);
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO1 = new PrimaryResubmissionServiceLinesMasterDTO();
        primaryResubmissionServiceLinesMasterDTO1.setChangeHealthPrimaryResubmisionServicelinesId(1L);
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO2 = new PrimaryResubmissionServiceLinesMasterDTO();
        assertThat(primaryResubmissionServiceLinesMasterDTO1).isNotEqualTo(primaryResubmissionServiceLinesMasterDTO2);
        primaryResubmissionServiceLinesMasterDTO2.setChangeHealthPrimaryResubmisionServicelinesId(
            primaryResubmissionServiceLinesMasterDTO1.getChangeHealthPrimaryResubmisionServicelinesId()
        );
        assertThat(primaryResubmissionServiceLinesMasterDTO1).isEqualTo(primaryResubmissionServiceLinesMasterDTO2);
        primaryResubmissionServiceLinesMasterDTO2.setChangeHealthPrimaryResubmisionServicelinesId(2L);
        assertThat(primaryResubmissionServiceLinesMasterDTO1).isNotEqualTo(primaryResubmissionServiceLinesMasterDTO2);
        primaryResubmissionServiceLinesMasterDTO1.setChangeHealthPrimaryResubmisionServicelinesId(null);
        assertThat(primaryResubmissionServiceLinesMasterDTO1).isNotEqualTo(primaryResubmissionServiceLinesMasterDTO2);
    }
}
