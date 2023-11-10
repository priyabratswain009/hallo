package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepreciationMethodDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepreciationMethodDTO.class);
        DepreciationMethodDTO depreciationMethodDTO1 = new DepreciationMethodDTO();
        depreciationMethodDTO1.setDepreciationMethodId(1L);
        DepreciationMethodDTO depreciationMethodDTO2 = new DepreciationMethodDTO();
        assertThat(depreciationMethodDTO1).isNotEqualTo(depreciationMethodDTO2);
        depreciationMethodDTO2.setDepreciationMethodId(depreciationMethodDTO1.getDepreciationMethodId());
        assertThat(depreciationMethodDTO1).isEqualTo(depreciationMethodDTO2);
        depreciationMethodDTO2.setDepreciationMethodId(2L);
        assertThat(depreciationMethodDTO1).isNotEqualTo(depreciationMethodDTO2);
        depreciationMethodDTO1.setDepreciationMethodId(null);
        assertThat(depreciationMethodDTO1).isNotEqualTo(depreciationMethodDTO2);
    }
}
