package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskDetailsDTO.class);
        TaskDetailsDTO taskDetailsDTO1 = new TaskDetailsDTO();
        taskDetailsDTO1.setTaskDetailsId(1L);
        TaskDetailsDTO taskDetailsDTO2 = new TaskDetailsDTO();
        assertThat(taskDetailsDTO1).isNotEqualTo(taskDetailsDTO2);
        taskDetailsDTO2.setTaskDetailsId(taskDetailsDTO1.getTaskDetailsId());
        assertThat(taskDetailsDTO1).isEqualTo(taskDetailsDTO2);
        taskDetailsDTO2.setTaskDetailsId(2L);
        assertThat(taskDetailsDTO1).isNotEqualTo(taskDetailsDTO2);
        taskDetailsDTO1.setTaskDetailsId(null);
        assertThat(taskDetailsDTO1).isNotEqualTo(taskDetailsDTO2);
    }
}
