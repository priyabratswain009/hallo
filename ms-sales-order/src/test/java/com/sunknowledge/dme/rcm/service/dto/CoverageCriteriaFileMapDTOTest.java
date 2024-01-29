package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CoverageCriteriaFileMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoverageCriteriaFileMapDTO.class);
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO1 = new CoverageCriteriaFileMapDTO();
        coverageCriteriaFileMapDTO1.setCoverageCriteriaFileMapId(1L);
        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO2 = new CoverageCriteriaFileMapDTO();
        assertThat(coverageCriteriaFileMapDTO1).isNotEqualTo(coverageCriteriaFileMapDTO2);
        coverageCriteriaFileMapDTO2.setCoverageCriteriaFileMapId(coverageCriteriaFileMapDTO1.getCoverageCriteriaFileMapId());
        assertThat(coverageCriteriaFileMapDTO1).isEqualTo(coverageCriteriaFileMapDTO2);
        coverageCriteriaFileMapDTO2.setCoverageCriteriaFileMapId(2L);
        assertThat(coverageCriteriaFileMapDTO1).isNotEqualTo(coverageCriteriaFileMapDTO2);
        coverageCriteriaFileMapDTO1.setCoverageCriteriaFileMapId(null);
        assertThat(coverageCriteriaFileMapDTO1).isNotEqualTo(coverageCriteriaFileMapDTO2);
    }
}
