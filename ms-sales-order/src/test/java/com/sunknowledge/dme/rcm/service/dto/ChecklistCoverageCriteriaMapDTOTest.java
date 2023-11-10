package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChecklistCoverageCriteriaMapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChecklistCoverageCriteriaMapDTO.class);
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO1 = new ChecklistCoverageCriteriaMapDTO();
        checklistCoverageCriteriaMapDTO1.setChecklistCoverageCriteriaId(1L);
        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO2 = new ChecklistCoverageCriteriaMapDTO();
        assertThat(checklistCoverageCriteriaMapDTO1).isNotEqualTo(checklistCoverageCriteriaMapDTO2);
        checklistCoverageCriteriaMapDTO2.setChecklistCoverageCriteriaId(checklistCoverageCriteriaMapDTO1.getChecklistCoverageCriteriaId());
        assertThat(checklistCoverageCriteriaMapDTO1).isEqualTo(checklistCoverageCriteriaMapDTO2);
        checklistCoverageCriteriaMapDTO2.setChecklistCoverageCriteriaId(2L);
        assertThat(checklistCoverageCriteriaMapDTO1).isNotEqualTo(checklistCoverageCriteriaMapDTO2);
        checklistCoverageCriteriaMapDTO1.setChecklistCoverageCriteriaId(null);
        assertThat(checklistCoverageCriteriaMapDTO1).isNotEqualTo(checklistCoverageCriteriaMapDTO2);
    }
}
