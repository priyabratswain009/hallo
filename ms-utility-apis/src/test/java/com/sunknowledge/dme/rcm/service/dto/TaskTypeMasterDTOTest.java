package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskTypeMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskTypeMasterDTO.class);
        TaskTypeMasterDTO taskTypeMasterDTO1 = new TaskTypeMasterDTO();
        taskTypeMasterDTO1.setTaskId(1L);
        TaskTypeMasterDTO taskTypeMasterDTO2 = new TaskTypeMasterDTO();
        assertThat(taskTypeMasterDTO1).isNotEqualTo(taskTypeMasterDTO2);
        taskTypeMasterDTO2.setTaskId(taskTypeMasterDTO1.getTaskId());
        assertThat(taskTypeMasterDTO1).isEqualTo(taskTypeMasterDTO2);
        taskTypeMasterDTO2.setTaskId(2L);
        assertThat(taskTypeMasterDTO1).isNotEqualTo(taskTypeMasterDTO2);
        taskTypeMasterDTO1.setTaskId(null);
        assertThat(taskTypeMasterDTO1).isNotEqualTo(taskTypeMasterDTO2);
    }
}
