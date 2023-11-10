package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskReasonMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskReasonMasterDTO.class);
        TaskReasonMasterDTO taskReasonMasterDTO1 = new TaskReasonMasterDTO();
        taskReasonMasterDTO1.setTaskReasonId(1L);
        TaskReasonMasterDTO taskReasonMasterDTO2 = new TaskReasonMasterDTO();
        assertThat(taskReasonMasterDTO1).isNotEqualTo(taskReasonMasterDTO2);
        taskReasonMasterDTO2.setTaskReasonId(taskReasonMasterDTO1.getTaskReasonId());
        assertThat(taskReasonMasterDTO1).isEqualTo(taskReasonMasterDTO2);
        taskReasonMasterDTO2.setTaskReasonId(2L);
        assertThat(taskReasonMasterDTO1).isNotEqualTo(taskReasonMasterDTO2);
        taskReasonMasterDTO1.setTaskReasonId(null);
        assertThat(taskReasonMasterDTO1).isNotEqualTo(taskReasonMasterDTO2);
    }
}
