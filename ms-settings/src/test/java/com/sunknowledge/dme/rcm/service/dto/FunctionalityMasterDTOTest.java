package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FunctionalityMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunctionalityMasterDTO.class);
        FunctionalityMasterDTO functionalityMasterDTO1 = new FunctionalityMasterDTO();
        functionalityMasterDTO1.setFunctionalityId(1L);
        FunctionalityMasterDTO functionalityMasterDTO2 = new FunctionalityMasterDTO();
        assertThat(functionalityMasterDTO1).isNotEqualTo(functionalityMasterDTO2);
        functionalityMasterDTO2.setFunctionalityId(functionalityMasterDTO1.getFunctionalityId());
        assertThat(functionalityMasterDTO1).isEqualTo(functionalityMasterDTO2);
        functionalityMasterDTO2.setFunctionalityId(2L);
        assertThat(functionalityMasterDTO1).isNotEqualTo(functionalityMasterDTO2);
        functionalityMasterDTO1.setFunctionalityId(null);
        assertThat(functionalityMasterDTO1).isNotEqualTo(functionalityMasterDTO2);
    }
}
