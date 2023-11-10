package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryServiceLinesMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryServiceLinesMasterDTO.class);
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO1 = new SecondaryServiceLinesMasterDTO();
        secondaryServiceLinesMasterDTO1.setChangeHealthSecondarySubmisionServicelinesId(1L);
        SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO2 = new SecondaryServiceLinesMasterDTO();
        assertThat(secondaryServiceLinesMasterDTO1).isNotEqualTo(secondaryServiceLinesMasterDTO2);
        secondaryServiceLinesMasterDTO2.setChangeHealthSecondarySubmisionServicelinesId(
            secondaryServiceLinesMasterDTO1.getChangeHealthSecondarySubmisionServicelinesId()
        );
        assertThat(secondaryServiceLinesMasterDTO1).isEqualTo(secondaryServiceLinesMasterDTO2);
        secondaryServiceLinesMasterDTO2.setChangeHealthSecondarySubmisionServicelinesId(2L);
        assertThat(secondaryServiceLinesMasterDTO1).isNotEqualTo(secondaryServiceLinesMasterDTO2);
        secondaryServiceLinesMasterDTO1.setChangeHealthSecondarySubmisionServicelinesId(null);
        assertThat(secondaryServiceLinesMasterDTO1).isNotEqualTo(secondaryServiceLinesMasterDTO2);
    }
}
