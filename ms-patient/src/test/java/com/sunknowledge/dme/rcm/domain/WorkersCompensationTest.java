package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkersCompensationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkersCompensation.class);
        WorkersCompensation workersCompensation1 = new WorkersCompensation();
        workersCompensation1.setWorkersCompensationId(1L);
        WorkersCompensation workersCompensation2 = new WorkersCompensation();
        workersCompensation2.setWorkersCompensationId(workersCompensation1.getWorkersCompensationId());
        assertThat(workersCompensation1).isEqualTo(workersCompensation2);
        workersCompensation2.setWorkersCompensationId(2L);
        assertThat(workersCompensation1).isNotEqualTo(workersCompensation2);
        workersCompensation1.setWorkersCompensationId(null);
        assertThat(workersCompensation1).isNotEqualTo(workersCompensation2);
    }
}
