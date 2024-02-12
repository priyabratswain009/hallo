package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CoverageCriteriaFileMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoverageCriteriaFileMap.class);
        CoverageCriteriaFileMap coverageCriteriaFileMap1 = new CoverageCriteriaFileMap();
        coverageCriteriaFileMap1.setCoverageCriteriaFileMapId(1L);
        CoverageCriteriaFileMap coverageCriteriaFileMap2 = new CoverageCriteriaFileMap();
        coverageCriteriaFileMap2.setCoverageCriteriaFileMapId(coverageCriteriaFileMap1.getCoverageCriteriaFileMapId());
        assertThat(coverageCriteriaFileMap1).isEqualTo(coverageCriteriaFileMap2);
        coverageCriteriaFileMap2.setCoverageCriteriaFileMapId(2L);
        assertThat(coverageCriteriaFileMap1).isNotEqualTo(coverageCriteriaFileMap2);
        coverageCriteriaFileMap1.setCoverageCriteriaFileMapId(null);
        assertThat(coverageCriteriaFileMap1).isNotEqualTo(coverageCriteriaFileMap2);
    }
}
