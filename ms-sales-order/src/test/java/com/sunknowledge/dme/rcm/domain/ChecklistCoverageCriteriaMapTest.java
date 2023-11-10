package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChecklistCoverageCriteriaMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChecklistCoverageCriteriaMap.class);
        ChecklistCoverageCriteriaMap checklistCoverageCriteriaMap1 = new ChecklistCoverageCriteriaMap();
        checklistCoverageCriteriaMap1.setChecklistCoverageCriteriaId(1L);
        ChecklistCoverageCriteriaMap checklistCoverageCriteriaMap2 = new ChecklistCoverageCriteriaMap();
        checklistCoverageCriteriaMap2.setChecklistCoverageCriteriaId(checklistCoverageCriteriaMap1.getChecklistCoverageCriteriaId());
        assertThat(checklistCoverageCriteriaMap1).isEqualTo(checklistCoverageCriteriaMap2);
        checklistCoverageCriteriaMap2.setChecklistCoverageCriteriaId(2L);
        assertThat(checklistCoverageCriteriaMap1).isNotEqualTo(checklistCoverageCriteriaMap2);
        checklistCoverageCriteriaMap1.setChecklistCoverageCriteriaId(null);
        assertThat(checklistCoverageCriteriaMap1).isNotEqualTo(checklistCoverageCriteriaMap2);
    }
}
