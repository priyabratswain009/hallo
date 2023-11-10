package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkersCompensationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkersCompensationDTO.class);
        WorkersCompensationDTO workersCompensationDTO1 = new WorkersCompensationDTO();
        workersCompensationDTO1.setWorkersCompensationId(1L);
        WorkersCompensationDTO workersCompensationDTO2 = new WorkersCompensationDTO();
        assertThat(workersCompensationDTO1).isNotEqualTo(workersCompensationDTO2);
        workersCompensationDTO2.setWorkersCompensationId(workersCompensationDTO1.getWorkersCompensationId());
        assertThat(workersCompensationDTO1).isEqualTo(workersCompensationDTO2);
        workersCompensationDTO2.setWorkersCompensationId(2L);
        assertThat(workersCompensationDTO1).isNotEqualTo(workersCompensationDTO2);
        workersCompensationDTO1.setWorkersCompensationId(null);
        assertThat(workersCompensationDTO1).isNotEqualTo(workersCompensationDTO2);
    }
}
